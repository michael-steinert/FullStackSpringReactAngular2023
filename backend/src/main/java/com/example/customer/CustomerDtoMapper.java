package com.example.customer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDto> {
  @Override
  public CustomerDto apply(Customer customer) {
    return new CustomerDto(
        customer.getId(),
        customer.getName(),
        customer.getEmail(),
        customer.getAge(),
        customer.getGender(),
        customer.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()),
        customer.getUsername());
  }
}