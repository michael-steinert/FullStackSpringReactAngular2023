package com.example.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  boolean existsCustomerById(Integer customerId);

  boolean existsCustomerByEmail(String email);

  Optional<Customer> findCustomerByEmail(String email);
}
