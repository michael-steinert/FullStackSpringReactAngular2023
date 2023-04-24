package com.example.customer;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.jwt.JwtUtil;

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

  @GetMapping(path = "{customerId}/image")
  public byte[] downloadCustomerImage(@PathVariable("customerId") Integer customerId) {
    return customerService.downloadCustomerImage(customerId);
  }

  @PostMapping(path = "{customerId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void uploadCustomerImage(
      @PathVariable("customerId") Integer customerId,
      @RequestParam("file") MultipartFile multipartFile) {
    customerService.uploadCustomerImage(customerId, multipartFile);
  }
}
