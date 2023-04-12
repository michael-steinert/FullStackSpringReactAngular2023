package com.example.customer;

import com.example.exception.DuplicateResourceException;
import com.example.exception.RequestValidationException;
import com.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

  private final CustomerDao customerDao;

  public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  public List<Customer> getAllCustomers() {
    return customerDao.selectAllCustomers();
  }

  public Customer getCustomer(Integer customerId) {
    return customerDao.selectCustomerById(customerId)
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
            customerRegistrationRequest.age(),
            customerRegistrationRequest.gender()));
  }

  public void updateCustomer(Integer customerId, CustomerUpdateRequest customerUpdateRequest) {
    Customer customer = getCustomer(customerId);
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
