package com.example.customer;

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
    return customerRepository.findAll();
  }

  @Override
  public Optional<Customer> selectCustomerById(Integer customerId) {
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
  public void removeCustomer(Integer customerId) {
    customerRepository.deleteById(customerId);
  }

  @Override
  public boolean existsCustomerWithId(Integer customerId) {
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
}
