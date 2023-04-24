package com.example.customer;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.exception.DuplicateResourceException;
import com.example.exception.RequestValidationException;
import com.example.exception.ResourceNotFoundException;
import com.example.s3.S3Buckets;
import com.example.s3.S3Service;

@Service
public class CustomerService {

  private final CustomerDao customerDao;
  private final PasswordEncoder passwordEncoder;
  private final CustomerDtoMapper customerDtoMapper;
  private final S3Service s3Service;
  private final S3Buckets s3Buckets;

  public CustomerService(
      @Qualifier("jpa") CustomerDao customerDao,
      PasswordEncoder passwordEncoder,
      CustomerDtoMapper customerDtoMapper,
      S3Service s3Service,
      S3Buckets s3Buckets) {
    this.customerDao = customerDao;
    this.passwordEncoder = passwordEncoder;
    this.customerDtoMapper = customerDtoMapper;
    this.s3Service = s3Service;
    this.s3Buckets = s3Buckets;
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

  public byte[] downloadCustomerImage(Integer customerId) {
    CustomerDto customer = customerDao.selectCustomerById(customerId)
        .map(customerDtoMapper)
        .orElseThrow(() -> new ResourceNotFoundException("Customer with ID %s not found".formatted(customerId)));
    if (customer.imageId() == null || customer.imageId().isEmpty()) {
      throw new ResourceNotFoundException("Image ID %s not found".formatted(customer.imageId()));
    }
    return s3Service.getObject(s3Buckets.getCustomerBucket(), "images/%s/%s".formatted(customerId, customer.imageId()));
  }

  public void uploadCustomerImage(Integer customerId, MultipartFile multipartFile) {
    if (!customerDao.existsCustomerWithId(customerId)) {
      throw new ResourceNotFoundException("Customer with ID %s not found".formatted(customerId));
    }
    String imageId = UUID.randomUUID().toString();
    try {
      s3Service.putObject(
          s3Buckets.getCustomerBucket(),
          "images/%s/%s".formatted(customerId, imageId),
          multipartFile.getBytes());
    } catch (IOException exception) {
      throw new RuntimeException("Failed to upload Customer Image", exception);
    }
    customerDao.updateCustomerImageId(imageId, customerId);
  }
}
