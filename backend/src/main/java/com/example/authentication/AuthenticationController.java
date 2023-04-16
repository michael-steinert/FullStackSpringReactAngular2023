package com.example.authentication;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  // Unbounded Wildcard <?> denote that the Response Body of the ResponseEntity
  // can be of any Type
  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
    AuthenticationResponse response = authenticationService.login(authenticationRequest);
    return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, response.jwt())
        .body(response.customer());
  }
}
