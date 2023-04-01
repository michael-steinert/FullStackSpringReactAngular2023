package com.example.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CustomerJpaDataAccessServiceTest {

    private CustomerJpaDataAccessService underTest;
    private AutoCloseable autoCloseable;
    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CustomerJpaDataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        // After each Test the Mock is closed and a new one is used
        autoCloseable.close();
    }

    @Test
    void selectAllCustomers() {
        // When
        underTest.selectAllCustomers();
        // Then
        verify(customerRepository).findAll();
    }

    @Test
    void selectCustomerById() {
        //Given
        int customerId = 1;
        // When
        underTest.selectCustomerById(customerId);
        // Then
        verify(customerRepository).findById(customerId);
    }

    @Test
    void insertCustomer() {
        // Given
        Customer customer = new Customer(1, "Bruno", "bruno@mail.com", 14);
        // When
        underTest.insertCustomer(customer);
        // Then
        verify(customerRepository).save(customer);
    }

    @Test
    void updateCustomer() {
        // Given
        Customer customer = new Customer(1, "Bruno", "bruno@mail.com", 14);
        // When
        underTest.updateCustomer(customer);
        // Then
        verify(customerRepository).save(customer);
    }

    @Test
    void removeCustomer() {
        // Given
        int customerId = 0;
        // When
        underTest.removeCustomer(customerId);
        // Then
        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void existsCustomerWithId() {
        // Given
        int customerId = 0;
        // When
        underTest.existsCustomerWithId(customerId);
        // Then
        verify(customerRepository).existsCustomerById(customerId);
    }

    @Test
    void existsCustomerWithEmail() {
        // Given
        String email = "name@gmail.com";
        // When
        underTest.existsCustomerWithEmail(email);
        // Then
        verify(customerRepository).existsCustomerByEmail(email);
    }
}