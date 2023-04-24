package com.example.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  boolean existsCustomerById(Integer customerId);

  boolean existsCustomerByEmail(String email);

  Optional<Customer> findCustomerByEmail(String email);

  // Automatically clear Entity Manager when a Query is executed to ensure that
  // Entities are not out of Synchronization
  @Modifying(clearAutomatically = true)
  @Query("UPDATE Customer c SET c.imageId = ?1 WHERE c.id = ?2")
  int updateImageId(String imageId, Integer customerId);
}
