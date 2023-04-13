package com.example.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class CustomerJdbcDataAccessService implements CustomerDao {

  private final JdbcTemplate jdbcTemplate;
  private final CustomerRowMapper customerRowMapper;

  public CustomerJdbcDataAccessService(
      JdbcTemplate jdbcTemplate,
      CustomerRowMapper customerRowMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.customerRowMapper = customerRowMapper;
  }

  @Override
  public List<Customer> selectAllCustomers() {
    var sql = """
        SELECT id, name, email, password, age, gender
        FROM customer
        """;
    return jdbcTemplate.query(sql, customerRowMapper);
  }

  @Override
  public Optional<Customer> selectCustomerById(Integer id) {
    var sql = """
        SELECT id, name, email, password, age, gender
        FROM customer
        WHERE id = ?
        """;
    return jdbcTemplate.query(sql, customerRowMapper, id)
        .stream()
        .findFirst();
  }

  @Override
  public void insertCustomer(Customer customer) {
    var sql = """
        INSERT INTO customer(name, email, password, age, gender)
        VALUES (?, ?, ?, ?, ?)
        """;
    int result = jdbcTemplate.update(
        sql,
        customer.getName(),
        customer.getEmail(),
        customer.getPassword(),
        customer.getAge(),
        customer.getGender().name());
    System.out.println("Insert Customer Result:" + result);
  }

  @Override
  public void updateCustomer(Customer update) {
    if (update.getName() != null) {
      String sql = "UPDATE customer SET name = ? WHERE id = ?";
      int result = jdbcTemplate.update(
          sql,
          update.getName(),
          update.getId());
      System.out.println("Update Customer Name Result:" + result);
    }
    if (update.getEmail() != null) {
      String sql = "UPDATE customer SET email = ? WHERE id = ?";
      int result = jdbcTemplate.update(
          sql,
          update.getEmail(),
          update.getId());
      System.out.println("Update Customer Email Result:" + result);
    }
    if (update.getPassword() != null) {
      String sql = "UPDATE customer SET password = ? WHERE id = ?";
      int result = jdbcTemplate.update(
          sql,
          update.getPassword(),
          update.getId());
      System.out.println("Update Customer Password Result:" + result);
    }
    if (update.getAge() != null) {
      String sql = "UPDATE customer SET age = ? WHERE id = ?";
      int result = jdbcTemplate.update(
          sql,
          update.getAge(),
          update.getId());
      System.out.println("Update Customer Age Result:" + result);
    }
    if (update.getGender() != null) {
      String sql = "UPDATE customer SET gender = ? WHERE id = ?";
      int result = jdbcTemplate.update(
          sql,
          update.getGender().name(),
          update.getId());
      System.out.println("Update Customer Gender Result:" + result);
    }
  }

  @Override
  public void removeCustomer(Integer customerId) {
    var sql = """
        DELETE
        FROM customer
        WHERE id = ?
        """;
    int result = jdbcTemplate.update(sql, customerId);
    System.out.println("Remove Customer by Id Result:" + result);
  }

  @Override
  public boolean existsCustomerWithId(Integer customerId) {
    var sql = """
        SELECT count(id)
        FROM customer
        WHERE id = ?
        """;
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, customerId);
    return count != null && count > 0;
  }

  @Override
  public boolean existsCustomerWithEmail(String email) {
    var sql = """
        SELECT count(id)
        FROM customer
        WHERE email = ?
        """;
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
    return count != null && count > 0;
  }

  @Override
  public Optional<Customer> selectUserByEmail(String email) {
    var sql = """
        SELECT id, name, email, password, age, gender
        FROM customer
        WHERE email = ?
        """;
    return jdbcTemplate.query(sql, customerRowMapper, email)
        .stream()
        .findFirst();
  }
}
