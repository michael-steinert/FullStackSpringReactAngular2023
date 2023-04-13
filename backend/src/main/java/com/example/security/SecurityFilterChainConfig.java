package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

  // Annotated as Bean to perform the Security Filter Chain on each Request
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        // HTML Forms are not used therefore no Need to enable CSRF
        .csrf().disable()
        // Only allow Post Requests to api/v1/customers without Authentication
        .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/api/v1/customers").permitAll()
        // All other Requests need to be authenticated
        .anyRequest().authenticated();
    return httpSecurity.build();
  }
}
