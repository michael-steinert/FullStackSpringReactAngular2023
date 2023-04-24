package com.example;

import com.example.customer.Customer;
import com.example.customer.CustomerRepository;
import com.example.customer.Gender;
import com.example.s3.S3Buckets;
import com.example.s3.S3Service;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class Main {
  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
  }

  @Bean
  CommandLineRunner runner(
      CustomerRepository customerRepository,
      PasswordEncoder passwordEncoder,
      S3Service s3Service,
      S3Buckets s3Buckets) {
    return args -> {
      createRandomCustomer(customerRepository, passwordEncoder);
      // testS3(s3Service, s3Buckets);
    };
  }

  private static void createRandomCustomer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
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
        passwordEncoder.encode("password"),
        age,
        gender);
    customerRepository.save(customer);
  }

  private static void testS3(S3Service s3Service, S3Buckets s3Buckets) {
    s3Service.putObject(s3Buckets.getCustomerBucket(), "my-object-key", "Bruno".getBytes());
    byte[] objectFromS3 = s3Service.getObject(s3Buckets.getCustomerBucket(), "my-object-key");
    System.out.println("Object from S3: %s".formatted(new String(objectFromS3)));
  }
}
