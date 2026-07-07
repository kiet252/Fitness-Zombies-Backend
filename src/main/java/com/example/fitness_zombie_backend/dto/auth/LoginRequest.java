package com.example.fitness_zombie_backend.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}