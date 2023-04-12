package com.example.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {

  private static final List<Customer> customers;

  static {
    customers = new ArrayList<>();
    Customer c1 = new Customer(
        42,
        "Bruno",
        "bruno@mail.com",
        14,
        Gender.MALE);
    Customer c2 = new Customer(
        43,
        "Bud",
        "bud@mail.com",
        4,
        Gender.MALE);
    customers.add(c1);
    customers.add(c2);
  }

  @Override
  public List<Customer> selectAllCustomers() {
    return customers;
  }

  @Override
  public Optional<Customer> selectCustomerById(Integer customerId) {
    return customers.stream()
        .filter(c -> c.getId().equals(customerId))
        .findFirst();
  }

  @Override
  public void insertCustomer(Customer customer) {
    customers.add(customer);
  }

  @Override
  public void updateCustomer(Customer customer) {
    customers.add(customer);
  }

  @Override
  public void removeCustomer(Integer customerId) {
    customers.stream()
        .filter(c -> c.getId().equals(customerId))
        .findFirst()
        .ifPresent(customers::remove);
  }

  @Override
  public boolean existsCustomerWithId(Integer customerId) {
    return customers.stream().anyMatch(c -> c.getId().equals(customerId));
  }

  @Override
  public boolean existsCustomerWithEmail(String email) {
    return customers.stream().anyMatch(c -> c.getEmail().equals(email));
  }
}
