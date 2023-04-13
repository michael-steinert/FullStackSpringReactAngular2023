package com.example.customer;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
  // A Row Mapper maps a Row to a Java Object
  // A ResultSet Object maintains an Iterator that points to its current Row of
  // Data
  @Override
  public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Customer(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("email"),
        rs.getString("password"),
        rs.getInt("age"),
        Gender.valueOf(rs.getString("gender"))

    );
  }
}