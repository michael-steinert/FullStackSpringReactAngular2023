package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

  private final AuthenticationProvider authenticationProvider;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationEntryPoint authenticationEntryPoint;

  public SecurityFilterChainConfig(AuthenticationProvider authenticationProvider,
      JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationEntryPoint authenticationEntryPoint) {
    this.authenticationProvider = authenticationProvider;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
  }

  // Annotated as Bean to perform the Security Filter Chain on each Request
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        // HTML Forms are not used therefore no Need to enable CSRF
        .csrf()
        .disable()
        .cors(Customizer.withDefaults())
        // Only allow Post Requests to api/v1/customers without Authentication
        .authorizeHttpRequests().requestMatchers(
            HttpMethod.POST,
            "/api/v1/customers",
            "/api/v1/authentication/login")
        .permitAll()
        .requestMatchers(
            HttpMethod.GET,
            "/actuator/**",
            "/api/v1/customers/*/image")
        .permitAll()
        // All other Requests need to be authenticated
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        // JWT is used for Authentication and therefore handles the State Management
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        // Register Custom Filter before Login through the Form (Username and Password)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        // Configure the proper Response Code
        .authenticationEntryPoint(authenticationEntryPoint);
    return httpSecurity.build();
  }
}
