package com.example.customer;

import java.util.List;

public record CustomerDto(
    Integer id,
    String name,
    String email,
    Integer age,
    Gender gender,
    List<String> roles,
    String username,
    String imageId) {
}
