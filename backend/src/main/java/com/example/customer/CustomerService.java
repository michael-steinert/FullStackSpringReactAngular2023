package com.example.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.exception.DuplicateResourceException;
import com.example.exception.RequestValidationException;
import com.example.exception.ResourceNotFoundException;

@Service
public class CustomerService {

  private final CustomerDao customerDao;
  private final PasswordEncoder passwordEncoder;
  private final CustomerDtoMapper customerDtoMapper;

  public CustomerService(
      @Qualifier("jpa") CustomerDao customerDao,
      PasswordEncoder passwordEncoder,
      CustomerDtoMapper customerDtoMapper) {
    this.customerDao = customerDao;
    this.passwordEncoder = passwordEncoder;
    this.customerDtoMapper = customerDtoMapper;
  }

  public List<CustomerDto> getAllCustomers() {
    return customerDao.selectAllCustomers().stream()
        .map(customerDtoMapper)
        .collect(Collectors.toList());
  }

  public CustomerDto getCustomer(Integer customerId) {
    return customerDao.selectCustomerById(customerId)
        .map(customerDtoMapper)
        .orElseThrow(() -> new ResourceNotFoundException("Customer with ID %s not found".formatted(customerId)));
  }

  public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
    String email = customerRegistrationRequest.email();
    if (customerDao.existsCustomerWithEmail(email)) {
      throw new DuplicateResourceException("Email %s already exists".formatted(email));
    }
    customerDao.insertCustomer(
        new Customer(
            customerRegistrationRequest.name(),
            email,
            passwordEncoder.encode(customerRegistrationRequest.password()),
            customerRegistrationRequest.age(),
            customerRegistrationRequest.gender()));
  }

  public void updateCustomer(Integer customerId, CustomerUpdateRequest customerUpdateRequest) {
    Customer customer = customerDao.selectCustomerById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Customer with ID %s not found".formatted(customerId)));
    boolean hasCustomerChanges = false;
    if (customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(customer.getName())) {
      customer.setName(customerUpdateRequest.name());
      hasCustomerChanges = true;
    }
    if (customerUpdateRequest.age() != null && !customerUpdateRequest.age().equals(customer.getAge())) {
      customer.setAge(customerUpdateRequest.age());
      hasCustomerChanges = true;
    }
    if (customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(customer.getEmail())) {
      if (customerDao.existsCustomerWithEmail(customerUpdateRequest.email())) {
        throw new DuplicateResourceException(
            "Email already exists");
      }
      customer.setEmail(customerUpdateRequest.email());
      hasCustomerChanges = true;
    }
    if (!hasCustomerChanges) {
      throw new RequestValidationException("No Customer Data Changes found");
    }
    customerDao.updateCustomer(customer);
  }

  public void removeCustomer(Integer customerId) {
    if (!customerDao.existsCustomerWithId(customerId)) {
      throw new ResourceNotFoundException("Customer with ID %s not found".formatted(customerId));
    }
    customerDao.removeCustomer(customerId);
  }
}
