package com.example.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
  List<Customer> selectAllCustomers();

  Optional<Customer> selectCustomerById(int customerId);

  void insertCustomer(Customer customer);

  void updateCustomer(Customer customer);

  void removeCustomer(int customerId);

  boolean existsCustomerWithId(int customerId);

  boolean existsCustomerWithEmail(String email);

  Optional<Customer> selectUserByEmail(String email);

  void updateCustomerImageId(String imageId, int customerId);
}
