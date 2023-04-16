package com.example.exception;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// `AuthenticationEntryPoint` is implemented by the Exception Translation Filter
// Exception Translation Filter is the first Entry Point for Spring Security
@Component
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final HandlerExceptionResolver handlerExceptionResolver;

  public DelegatedAuthenticationEntryPoint(
      @Qualifier("HandlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
    this.handlerExceptionResolver = handlerExceptionResolver;
  }

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    handlerExceptionResolver.resolveException(request, response, null, authException);
  }
}