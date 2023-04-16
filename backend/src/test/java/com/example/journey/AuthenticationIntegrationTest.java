package com.example.journey;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.authentication.AuthenticationRequest;
import com.example.authentication.AuthenticationResponse;
import com.example.customer.CustomerDto;
import com.example.customer.CustomerRegistrationRequest;
import com.example.customer.Gender;
import com.example.jwt.JwtUtil;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AuthenticationIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;
  @Autowired
  private JwtUtil jwtUtil;
  private static final Random RANDOM = new Random();
  private static final String CUSTOMER_PATH = "/api/v1/customers";
  private static final String AUTHENTICATION_PATH = "/api/v1/authentication";

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
    AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);
    // Send a Post Request to login an unauthorized Customer
    webTestClient.post()
        .uri(AUTHENTICATION_PATH + "/login")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
        .exchange()
        .expectStatus()
        .isUnauthorized();
    CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
        name,
        email,
        password,
        age,
        gender);
    // Send a Post Request to create a Customer
    webTestClient.post()
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
        .isOk();
    // Send a Post Request to login an authorized Customer
    EntityExchangeResult<AuthenticationResponse> entityExchangeResult = webTestClient.post()
        .uri(AUTHENTICATION_PATH + "/login")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(new ParameterizedTypeReference<AuthenticationResponse>() {
        })
        .returnResult();
    String jwt = entityExchangeResult.getResponseHeaders().get(AUTHORIZATION).get(0);
    AuthenticationResponse authenticationResponse = entityExchangeResult.getResponseBody();
    CustomerDto customer = authenticationResponse.customer();
    assertThat(jwtUtil.isJwtValid(jwt, customer.username())).isTrue();
    assertThat(customer.name()).isEqualTo(name);
    assertThat(customer.email()).isEqualTo(email);
    assertThat(customer.age()).isEqualTo(age);
    assertThat(customer.gender()).isEqualTo(gender);
    assertThat(customer.username()).isEqualTo(email);
    assertThat(customer.roles()).isEqualTo(List.of("ROLE_USER"));
  }
}
