package com.example.customer;

import com.example.SetupTestcontainersSuite;
import com.example.TestConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

// Loads only Beans that are needed for JPA Tests in the Application Context
@DataJpaTest
// Using Test Database (from Testcontainers) instead of embedded Database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Using custom Config for Tests
@Import({ TestConfig.class })
class CustomerRepositoryTest extends SetupTestcontainersSuite {

  @Autowired
  private CustomerRepository underTest;

  @BeforeEach
  void setUp() {
    underTest.deleteAll();
  }

  @Test
  void existsCustomerById() {
    // Given
    String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
    Customer customer = new Customer(
        FAKER.name().fullName(),
        email,
        "password",
        29,
        Gender.MALE);
    underTest.save(customer);
    int customerId = underTest.findAll()
        .stream()
        .filter(c -> c.getEmail().equals(email))
        .map(Customer::getId)
        .findFirst()
        .orElseThrow();
    // When
    boolean existsCustomer = underTest.existsCustomerById(customerId);
    // Then
    assertThat(existsCustomer).isTrue();
  }

  @Test
  void existsCustomerByIdFailsWhenIdNotPresent() {
    // Given
    int customerId = 0;
    // When
    boolean existsCustomer = underTest.existsCustomerById(customerId);
    // Then
    assertThat(existsCustomer).isFalse();
  }

  @Test
  void existsCustomerByEmail() {
    // Given
    String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
    Customer customer = new Customer(
        FAKER.name().fullName(),
        email,
        "password",
        29,
        Gender.MALE);
    underTest.save(customer);
    // When
    boolean existsCustomer = underTest.existsCustomerByEmail(email);
    // Then
    assertThat(existsCustomer).isTrue();
  }

  @Test
  void existsCustomerByEmailFailsWhenEmailNotPresent() {
    // Given
    String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
    // When
    boolean existsCustomer = underTest.existsCustomerByEmail(email);
    // Then
    assertThat(existsCustomer).isFalse();
  }

  @Test
  void canUpdateCustomerImageId() {
    // Given
    String email = FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID();
    Customer customer = new Customer(
        FAKER.name().fullName(),
        email,
        "password",
        29,
        Gender.MALE);
    underTest.save(customer);
    int customerId = underTest.findAll()
        .stream()
        .filter(c -> c.getEmail().equals(email))
        .map(Customer::getId)
        .findFirst()
        .orElseThrow();
    // When
    String imageId = "imageId";
    int result = underTest.updateImageId(imageId, customerId);
    // Then
    assertThat(result).isEqualTo(1);
    Optional<Customer> optionalCustomer = underTest.findById(customerId);
    assertThat(optionalCustomer).isPresent().hasValueSatisfying(c -> {
      assertThat(c.getImageId()).isEqualTo(imageId);
    });
  }
}