package com.example.fitness_zombie_backend.dto.auth;

public record RegisterRequest(
        String fullName,
        String email,
        String password
) {
}