package com.example.authentication;

public record AuthenticationRequest(
    String username,
    String password) {
}
