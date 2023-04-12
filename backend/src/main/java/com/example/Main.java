package com.example;

import com.example.customer.Customer;
import com.example.customer.CustomerRepository;
import com.example.customer.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
  }

  @Bean
  CommandLineRunner runner(CustomerRepository customerRepository) {
    return args -> {
      var faker = new Faker();
      Random random = new Random();
      Name name = faker.name();
      String firstName = name.firstName();
      String lastName = name.lastName();
      int age = random.nextInt(16, 99);
      Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;
      Customer customer = new Customer(
          firstName + " " + lastName,
          firstName.toLowerCase() + "." + lastName.toLowerCase() + "@mail.com",
          age,
          gender);
      customerRepository.save(customer);
    };
  }
}
