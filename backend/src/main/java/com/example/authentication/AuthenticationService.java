package com.example.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.customer.Customer;
import com.example.customer.CustomerDto;
import com.example.customer.CustomerDtoMapper;
import com.example.jwt.JwtUtil;

@Service
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final CustomerDtoMapper customerDtoMapper;

  public AuthenticationService(
      AuthenticationManager authenticationManager,
      JwtUtil jwtUtil,
      CustomerDtoMapper customerDtoMapper) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.customerDtoMapper = customerDtoMapper;
  }

  public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest, authenticationRequest));
    // Cast PRincipal Object into Customer
    Customer principal = (Customer) authentication.getPrincipal();
    CustomerDto customer = customerDtoMapper.apply(principal);
    // Issue Token for Customer
    String jwt = jwtUtil.issueJwtToken(customer.username(), customer.roles());
    return new AuthenticationResponse(customer, jwt);
  }
}
