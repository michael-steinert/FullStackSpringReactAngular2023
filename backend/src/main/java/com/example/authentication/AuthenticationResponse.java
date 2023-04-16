package com.example.authentication;

import com.example.customer.CustomerDto;

public record AuthenticationResponse(
    CustomerDto customer,
    String jwt) {
}
