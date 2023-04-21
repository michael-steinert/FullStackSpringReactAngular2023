package com.example.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
  void canSelectAllCustomers() {
    Page<Customer> customerPage = mock(Page.class);
    List<Customer> customers = List.of(new Customer());
    when(customerPage.getContent()).thenReturn(customers);
    when(customerRepository.findAll(any(Pageable.class))).thenReturn(customerPage);
    // When
    List<Customer> expectedCustomers = underTest.selectAllCustomers();
    // Then
    assertThat(expectedCustomers).isEqualTo(customers);
    ArgumentCaptor<Pageable> pageArgumentCaptor = ArgumentCaptor.forClass(Pageable.class);
    verify(customerRepository).findAll(pageArgumentCaptor.capture());
    assertThat(pageArgumentCaptor.getValue()).isEqualTo(Pageable.ofSize(42));
  }

  @Test
  void caSelectCustomerById() {
    // Given
    int customerId = 1;
    // When
    underTest.selectCustomerById(customerId);
    // Then
    verify(customerRepository).findById(customerId);
  }

  @Test
  void canInsertCustomer() {
    // Given
    Customer customer = new Customer(
        1,
        "Bruno",
        "bruno@mail.com",
        "password",
        14,
        Gender.MALE);
    // When
    underTest.insertCustomer(customer);
    // Then
    verify(customerRepository).save(customer);
  }

  @Test
  void canUpdateCustomer() {
    // Given
    Customer customer = new Customer(
        1,
        "Bruno",
        "bruno@mail.com",
        "password",
        14,
        Gender.MALE);
    // When
    underTest.updateCustomer(customer);
    // Then
    verify(customerRepository).save(customer);
  }

  @Test
  void canRemoveCustomer() {
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