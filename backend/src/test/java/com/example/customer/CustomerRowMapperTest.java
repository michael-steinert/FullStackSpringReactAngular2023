package com.example.customer;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRowMapperTest {

    @Test
    void canMapRow() throws SQLException {
        // Given
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("age")).thenReturn(14);
        when(resultSet.getString("name")).thenReturn("Bruno");
        when(resultSet.getString("email")).thenReturn("bruno@mail.com");
        when(resultSet.getString("gender")).thenReturn("MALE");
        // When
        Customer actualCustomer = customerRowMapper.mapRow(resultSet, 1);
        // Then
        Customer expectedCustomer = new Customer(
                1,
                "Bruno",
                "bruno@mail.com",
                14,
                Gender.MALE
        );
        assertThat(actualCustomer).isEqualTo(expectedCustomer);
    }
}