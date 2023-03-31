package com.example;

import com.example.customer.Customer;
import com.example.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer c1 = new Customer("Bruno", "bruno@mail.com", 14);
            Customer c2 = new Customer("Bud", "bud@mail.com", 4);
            List<Customer> customers = List.of(c1, c2);
            customerRepository.saveAll(customers);
        };
    }
}
