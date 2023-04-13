package com.example.journey;

import com.example.customer.Customer;
import com.example.customer.CustomerRegistrationRequest;
import com.example.customer.CustomerUpdateRequest;
import com.example.customer.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;
  private static final Random RANDOM = new Random();
  private static final String CUSTOMER_URI = "/api/v1/customers";

  @Test
  void canInsertCustomer() {
    // Create Customer Registration Request
    Faker faker = new Faker();
    Name fakerName = faker.name();
    String name = fakerName.fullName();
    String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@mail.com";
    String password = "password";
    int age = RANDOM.nextInt(1, 100);
    Gender gender = Gender.MALE;
    CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(name, email,
        password, age, gender);
    // Send a Post Request to create a Customer
    webTestClient.post()
        .uri(CUSTOMER_URI)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(customerRegistrationRequest), CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk();
    // Send a Get Request to get all Customers
    List<Customer> customers = webTestClient.get()
        .uri(CUSTOMER_URI)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<Customer>() {
        })
        .returnResult()
        .getResponseBody();
    // Checking that expected Customer is present
    Customer expectedCustomer = new Customer(name, email, password, age, gender);
    assertThat(customers)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
        .contains(expectedCustomer);
    int customerId = customers.stream()
        .filter(customer -> customer.getEmail().equals(email))
        .map(Customer::getId)
        .findFirst()
        .orElseThrow();
    expectedCustomer.setId(customerId);
    // Send a Get Request to get Customer by ID
    webTestClient.get()
        .uri(CUSTOMER_URI + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(new ParameterizedTypeReference<Customer>() {
        })
        .isEqualTo(expectedCustomer);
  }

  @Test
  void canUpdateCustomer() {
    // Create Customer Registration Request
    Faker faker = new Faker();
    Name fakerName = faker.name();
    String name = fakerName.fullName();
    String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@mail.com";
    String password = "password";
    int age = RANDOM.nextInt(1, 100);
    Gender gender = Gender.MALE;
    CustomerRegistrationRequest request = new CustomerRegistrationRequest(name, email, password, age, gender);
    // Send a Post Request to create a Customer
    webTestClient.post()
        .uri(CUSTOMER_URI)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(request), CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk();
    // Send a Get Request to get all Customers
    List<Customer> allCustomers = webTestClient.get()
        .uri(CUSTOMER_URI)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<Customer>() {
        })
        .returnResult()
        .getResponseBody();
    // Checking that expected Customer is present
    int customerId = allCustomers.stream()
        .filter(customer -> customer.getEmail().equals(email))
        .map(Customer::getId)
        .findFirst()
        .orElseThrow();
    // Create Customer Update Request
    String newName = "Bruns";
    CustomerUpdateRequest updateRequest = new CustomerUpdateRequest(
        newName,
        null,
        null,
        null,
        null);
    // Send a Put Request to update Customer
    webTestClient.put()
        .uri(CUSTOMER_URI + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(updateRequest), CustomerUpdateRequest.class)
        .exchange()
        .expectStatus()
        .isOk();
    // Checking that expected Customer is present
    Customer updatedCustomer = webTestClient.get()
        .uri(CUSTOMER_URI + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(Customer.class)
        .returnResult()
        .getResponseBody();
    Customer expectedCustomer = new Customer(customerId, newName, email, password, age, gender);
    assertThat(updatedCustomer).isEqualTo(expectedCustomer);
  }

  @Test
  void canRemoveCustomer() {
    // Create Customer Registration Request
    Faker faker = new Faker();
    Name fakerName = faker.name();
    String name = fakerName.fullName();
    String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@mail.com";
    String password = "password";
    int age = RANDOM.nextInt(1, 100);
    Gender gender = Gender.MALE;
    CustomerRegistrationRequest request = new CustomerRegistrationRequest(name, email, password, age, gender);
    // Send a Post Request to create a Customer
    webTestClient.post()
        .uri(CUSTOMER_URI)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(request), CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk();
    // Send a Get Request to get all Customers
    List<Customer> customers = webTestClient.get()
        .uri(CUSTOMER_URI)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<Customer>() {
        })
        .returnResult()
        .getResponseBody();
    // Checking that expected Customer is present
    int customerId = customers.stream()
        .filter(customer -> customer.getEmail().equals(email))
        .map(Customer::getId)
        .findFirst()
        .orElseThrow();
    // Send a Delete Request to delete Customer by ID
    webTestClient.delete()
        .uri(CUSTOMER_URI + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk();
    // Send a Get Request to get Customer by ID
    webTestClient.get()
        .uri(CUSTOMER_URI + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isNotFound();
  }
}
