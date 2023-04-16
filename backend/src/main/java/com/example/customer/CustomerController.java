package com.example.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jwt.JwtUtil;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;
  private final JwtUtil jwtUtil;

  public CustomerController(CustomerService customerService, JwtUtil jwtUtil) {
    this.customerService = customerService;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping
  public List<CustomerDto> getCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping(path = "{customerId}")
  public CustomerDto getCustomer(@PathVariable("customerId") Integer customerId) {
    return customerService.getCustomer(customerId);
  }

  // Unbounded Wildcard <?> denote that the Response Body of the ResponseEntity
  // can be of any Type
  @PostMapping
  public ResponseEntity<?> addCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
    customerService.addCustomer(customerRegistrationRequest);  
    String jwt = jwtUtil.issueJwtToken(customerRegistrationRequest.email(), "ROLE_USER");
    // Sending JWT to User
    return ResponseEntity.ok().header(AUTHORIZATION, jwt).build();
  }

  @PutMapping(path = "{customerId}")
  public void updateCustomer(
      @PathVariable("customerId") Integer customerId,
      @RequestBody CustomerUpdateRequest customerUpdateRequest) {
    customerService.updateCustomer(customerId, customerUpdateRequest);
  }

  @DeleteMapping(path = "{customerId}")
  public void removeCustomer(@PathVariable("customerId") Integer customerId) {
    customerService.removeCustomer(customerId);
  }
}
