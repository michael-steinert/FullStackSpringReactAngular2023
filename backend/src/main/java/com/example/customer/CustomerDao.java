package com.example.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(Integer customerId);

    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void removeCustomer(Integer customerId);

    boolean existsCustomerWithId(Integer customerId);

    boolean existsCustomerWithEmail(String email);
}
