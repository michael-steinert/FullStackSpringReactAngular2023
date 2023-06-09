package com.example.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerJpaDataAccessService implements CustomerDao {

  private final CustomerRepository customerRepository;

  public CustomerJpaDataAccessService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> selectAllCustomers() {
    Page<Customer> customerPage = customerRepository.findAll(Pageable.ofSize(42));
    return customerPage.getContent();
  }

  @Override
  public Optional<Customer> selectCustomerById(int customerId) {
    return customerRepository.findById(customerId);
  }

  @Override
  public void insertCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void updateCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void removeCustomer(int customerId) {
    customerRepository.deleteById(customerId);
  }

  @Override
  public boolean existsCustomerWithId(int customerId) {
    return customerRepository.existsCustomerById(customerId);
  }

  @Override
  public boolean existsCustomerWithEmail(String email) {
    return customerRepository.existsCustomerByEmail(email);
  }

  @Override
  public Optional<Customer> selectUserByEmail(String email) {
    return customerRepository.findCustomerByEmail(email);
  }

  @Override
  public void updateCustomerImageId(String imageId, int customerId) {
    customerRepository.updateImageId(imageId, customerId);
  }
}
