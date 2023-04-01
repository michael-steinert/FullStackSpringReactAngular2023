package com.example.customer;

import com.example.SetupTestcontainersSuite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerJdbcDataAccessServiceTest extends SetupTestcontainersSuite {

    private CustomerJdbcDataAccessService customerJdbcDataAccessService;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        // Before each Test a new Instance is created
        customerJdbcDataAccessService = new CustomerJdbcDataAccessService(getJdbcTemplate(), customerRowMapper);
    }

    @Test
    void selectAllCustomers() {
        // Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID(),
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        // When
        List<Customer> actualCustomers = customerJdbcDataAccessService.selectAllCustomers();
        // Then
        assertThat(actualCustomers).isNotEmpty();
    }


    @Test
    void selectCustomerById() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        int customerId = customerJdbcDataAccessService.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        // When
        Optional<Customer> actualCustomer = customerJdbcDataAccessService.selectCustomerById(customerId);
        // Then
        assertThat(actualCustomer).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void willReturnEmptyWhenSelectCustomerById() {
        // Given
        int customerId = 0;
        // When
        var actualCustomer = customerJdbcDataAccessService.selectCustomerById(customerId);
        // Then
        assertThat(actualCustomer).isEmpty();
    }

    @Test
    void insertCustomer() {
        // Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID(),
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        // When
        List<Customer> actualCustomers = customerJdbcDataAccessService.selectAllCustomers();
        // Then
        assertThat(actualCustomers).isNotEmpty();
    }

    @Test
    void updateCustomer() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        int customerId = customerJdbcDataAccessService.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        String newName = "Bella";
        // When
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setName(newName);
        customerJdbcDataAccessService.updateCustomer(updatedCustomer);
        // Then
        Optional<Customer> actualCustomer = customerJdbcDataAccessService.selectCustomerById(customerId);
        assertThat(actualCustomer).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getName()).isEqualTo(newName);
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void willNotUpdateWhenNothingToUpdate() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        int customerId = customerJdbcDataAccessService.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        // When
        Customer notUpdatedCustomer = new Customer();
        notUpdatedCustomer.setId(customerId);
        customerJdbcDataAccessService.updateCustomer(notUpdatedCustomer);
        // Then
        Optional<Customer> actualCustomer = customerJdbcDataAccessService.selectCustomerById(customerId);
        assertThat(actualCustomer).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(customerId);
            assertThat(c.getAge()).isEqualTo(customer.getAge());
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
        });
    }

    @Test
    void removeCustomer() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        int customerId = customerJdbcDataAccessService.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        // When
        customerJdbcDataAccessService.removeCustomer(customerId);
        // Then
        Optional<Customer> actualCustomer = customerJdbcDataAccessService.selectCustomerById(customerId);
        assertThat(actualCustomer).isNotPresent();
    }

    @Test
    void existsCustomerWithId() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        int customerId = customerJdbcDataAccessService.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        // When
        var actualCustomer = customerJdbcDataAccessService.existsCustomerWithId(customerId);
        // Then
        assertThat(actualCustomer).isTrue();
    }

    @Test
    void existsCustomerWithIdWillReturnFalseWhenIdNotPresent() {
        // Given
        int customerId = 0;
        // When
        var actualCustomer = customerJdbcDataAccessService.existsCustomerWithId(customerId);
        // Then
        assertThat(actualCustomer).isFalse();
    }

    @Test
    void existsCustomerWithEmail() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        String name = FAKER.name().fullName();
        Customer customer = new Customer(
                name,
                email,
                29
        );
        customerJdbcDataAccessService.insertCustomer(customer);
        // When
        boolean actualCustomer = customerJdbcDataAccessService.existsCustomerWithEmail(email);
        // Then
        assertThat(actualCustomer).isTrue();
    }

    @Test
    void existsCustomerWithEmailReturnsFalseWhenDoesNotExists() {
        // Given
        String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
        // When
        boolean actualCustomer = customerJdbcDataAccessService.existsCustomerWithEmail(email);
        // Then
        assertThat(actualCustomer).isFalse();
    }
}