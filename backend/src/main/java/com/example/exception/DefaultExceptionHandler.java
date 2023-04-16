package com.example.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ControllerAdvice can catch an Exception and translate it into a Payload suitable for the Client
@ControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleException(
      ResourceNotFoundException notFoundException,
      HttpServletRequest httpServletRequest) {
    ApiError apiError = new ApiError(httpServletRequest.getRequestURI(),
        notFoundException.getMessage(),
        HttpStatus.NOT_FOUND.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InsufficientAuthenticationException.class)
  public ResponseEntity<ApiError> handleException(
      InsufficientAuthenticationException authenticationException,
      HttpServletRequest httpServletRequest) {
    ApiError apiError = new ApiError(httpServletRequest.getRequestURI(),
        authenticationException.getMessage(),
        HttpStatus.FORBIDDEN.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiError> handleException(
      BadCredentialsException badCredentialsException,
      HttpServletRequest httpServletRequest) {
    ApiError apiError = new ApiError(httpServletRequest.getRequestURI(),
        badCredentialsException.getMessage(),
        HttpStatus.UNAUTHORIZED.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(
      Exception exception,
      HttpServletRequest httpServletRequest) {
    ApiError apiError = new ApiError(httpServletRequest.getRequestURI(),
        exception.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        LocalDateTime.now());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
