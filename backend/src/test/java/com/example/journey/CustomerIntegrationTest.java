package com.example.journey;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testcontainers.shaded.com.google.common.io.Files;

import com.example.customer.CustomerDto;
import com.example.customer.CustomerRegistrationRequest;
import com.example.customer.CustomerUpdateRequest;
import com.example.customer.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;
  private static final Random RANDOM = new Random();
  private static final String CUSTOMER_PATH = "/api/v1/customers";

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
    String jwt = webTestClient.post()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        // Mono is a reactive Type that represents a Stream of zero or one Element
        // It is used to represent asynchronous, single-valued Computations that may
        // produce a Result or fail with an Error
        // It is a publisher that emits at most one Item
        .body(Mono.just(customerRegistrationRequest), CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk()
        .returnResult(Void.class)
        .getResponseHeaders()
        .get(AUTHORIZATION)
        // Returned List of Headers contains only one Element i.e. get(0)
        .get(0);
    // Send a Get Request to get all Customers
    List<CustomerDto> customers = webTestClient.get()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<CustomerDto>() {
        })
        .returnResult()
        .getResponseBody();
    int customerId = customers.stream()
        .filter(customer -> customer.email().equals(email))
        .map(CustomerDto::id)
        .findFirst()
        .orElseThrow();
    // Checking that expected Customer is present
    CustomerDto expectedCustomer = new CustomerDto(
        customerId,
        name,
        email,
        age,
        gender,
        List.of("ROLE_USER"),
        email,
        null);
    assertThat(customers)
        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
        .contains(expectedCustomer);
    // Send a Get Request to get Customer by ID
    webTestClient.get()
        .uri(CUSTOMER_PATH + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(new ParameterizedTypeReference<CustomerDto>() {
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
    CustomerRegistrationRequest request = new CustomerRegistrationRequest(
        name,
        email,
        password,
        age,
        gender);
    // Send a Post Request to create a Customer
    String jwt = webTestClient.post()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(request), CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk()
        .returnResult(Void.class)
        .getResponseHeaders()
        .get(AUTHORIZATION)
        // Returned List of Headers contains only one Element i.e. get(0)
        .get(0);
    // Send a Get Request to get all Customers
    List<CustomerDto> customers = webTestClient.get()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<CustomerDto>() {
        })
        .returnResult()
        .getResponseBody();
    // Checking that expected Customer is present
    int customerId = customers.stream()
        .filter(customer -> customer.email().equals(email))
        .map(CustomerDto::id)
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
        .uri(CUSTOMER_PATH + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(updateRequest), CustomerUpdateRequest.class)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk();
    // Checking that expected Customer is present
    CustomerDto updatedCustomer = webTestClient.get()
        .uri(CUSTOMER_PATH + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CustomerDto.class)
        .returnResult()
        .getResponseBody();
    CustomerDto expectedCustomer = new CustomerDto(
        customerId,
        newName,
        email,
        age,
        gender,
        List.of("ROLE_USER"),
        email,
        null);
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
    CustomerRegistrationRequest firstCustomerRequest = new CustomerRegistrationRequest(
        name,
        email,
        password,
        age,
        gender);
    CustomerRegistrationRequest secondCustomerRequest = new CustomerRegistrationRequest(
        name,
        email.replace(".com", ".org"),
        password,
        age,
        gender);
    // Send a Post Request to create first Customer
    webTestClient.post()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            Mono.just(firstCustomerRequest),
            CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk();
    // Send a Post Request to create a second Customer who passes his JWT to delete
    // the first Customer
    String jwt = webTestClient.post()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            Mono.just(secondCustomerRequest),
            CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk()
        .returnResult(Void.class)
        .getRequestHeaders()
        .get(AUTHORIZATION)
        // Returned List of Headers contains only one Element i.e. get(0)
        .get(0);
    // Send a Get Request to get all Customers
    List<CustomerDto> customers = webTestClient.get()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<CustomerDto>() {
        })
        .returnResult()
        .getResponseBody();
    // Checking that expected Customer is present
    int customerId = customers.stream()
        .filter(customer -> customer.email().equals(email))
        .map(CustomerDto::id)
        .findFirst()
        .orElseThrow();
    // Send a Delete Request to delete first Customer by ID with the JWT of the
    // second Customer
    webTestClient.delete()
        .uri(CUSTOMER_PATH + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk();
    // Send a Get Request to get Customer by ID with the JWT of the second Customer
    webTestClient.get()
        .uri(CUSTOMER_PATH + "/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  void canUploadAndDownloadCustomerImage() throws IOException {
    // Create Customer Registration Request
    Faker faker = new Faker();
    Name fakerName = faker.name();
    String name = fakerName.fullName();
    String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@mail.com";
    String password = "password";
    int age = RANDOM.nextInt(1, 100);
    Gender gender = Gender.MALE;
    CustomerRegistrationRequest request = new CustomerRegistrationRequest(
        name,
        email,
        password,
        age,
        gender);
    // Send a Post Request to create a Customer
    String jwt = webTestClient.post()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(request), CustomerRegistrationRequest.class)
        .exchange()
        .expectStatus()
        .isOk()
        .returnResult(Void.class)
        .getResponseHeaders()
        .get(AUTHORIZATION)
        // Returned List of Headers contains only one Element i.e. get(0)
        .get(0);
    // Send a Get Request to get all Customers
    List<CustomerDto> customers = webTestClient.get()
        .uri(CUSTOMER_PATH)
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(new ParameterizedTypeReference<CustomerDto>() {
        })
        .returnResult()
        .getResponseBody();
    // Checking that expected Customer is present
    CustomerDto customer = customers.stream()
        .filter(c -> c.email().equals(email))
        .findFirst()
        .orElseThrow();
    assertThat(customer.imageId()).isNullOrEmpty();
    Resource file = new ClassPathResource("dog1.jpg");
    MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
    multipartBodyBuilder.part("file", file);
    // Send a Post Request to upload a Image
    webTestClient.post()
        .uri(CUSTOMER_PATH + "/{customerId}/image", customer.id())
        .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk();
    // Send a Get Request get updated Image ID
    String updatedImageId = webTestClient.get()
        .uri(CUSTOMER_PATH + "/{customerId}", customer.id())
        .accept(MediaType.APPLICATION_JSON)
        .header(AUTHORIZATION, String.format("Bearer %s", jwt))
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(CustomerDto.class)
        .returnResult()
        .getResponseBody()
        .imageId();
    assertThat(updatedImageId).isNotEmpty();
    // Send a Get Request get uploaded Image
    byte[] downloadedFile = webTestClient.get()
        .uri(CUSTOMER_PATH + "/{customerId}/image", customer.id())
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(byte[].class)
        .returnResult()
        .getResponseBody();
    byte[] actualFile = Files.toByteArray(file.getFile());
    assertThat(actualFile).isEqualTo(downloadedFile);
  }
}
