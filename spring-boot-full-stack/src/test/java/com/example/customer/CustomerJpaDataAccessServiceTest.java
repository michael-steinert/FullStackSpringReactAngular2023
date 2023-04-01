package com.example.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class CustomerJpaDataAccessServiceTest {

    private CustomerJpaDataAccessService customerJpaDataAccessService;
    private AutoCloseable autoCloseable;
    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        customerJpaDataAccessService = new CustomerJpaDataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        // After each Test the Mock is closed and a new one is used
        autoCloseable.close();
    }

    @Test
    void selectAllCustomers() {
        // When
        customerJpaDataAccessService.selectAllCustomers();
        // Then
        verify(customerRepository).findAll();
    }

    @Test
    void selectCustomerById() {
        //Given
        int customerId = 1;
        // When
        customerJpaDataAccessService.selectCustomerById(customerId);
        // Then
        verify(customerRepository).findById(customerId);
    }

    @Test
    void insertCustomer() {
        // Given
        Customer customer = new Customer(1, "Bruno", "bruno@mail.com", 14);
        // When
        customerJpaDataAccessService.insertCustomer(customer);
        // Then
        verify(customerRepository).save(customer);
    }

    @Test
    void updateCustomer() {
        // Given
        Customer customer = new Customer(1, "Bruno", "bruno@mail.com", 14);
        // When
        customerJpaDataAccessService.updateCustomer(customer);
        // Then
        verify(customerRepository).save(customer);
    }

    @Test
    void removeCustomer() {
        // Given
        int customerId = 0;
        // When
        customerJpaDataAccessService.removeCustomer(customerId);
        // Then
        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void existsCustomerWithId() {
        // Given
        int customerId = 0;
        // When
        customerJpaDataAccessService.existsCustomerWithId(customerId);
        // Then
        verify(customerRepository).existsCustomerById(customerId);
    }

    @Test
    void existsCustomerWithEmail() {
        // Given
        String email = "name@gmail.com";
        // When
        customerJpaDataAccessService.existsCustomerWithEmail(email);
        // Then
        verify(customerRepository).existsCustomerByEmail(email);
    }
}