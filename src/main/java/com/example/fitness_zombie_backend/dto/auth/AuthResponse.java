package com.example.fitness_zombie_backend.dto.auth;

public record AuthResponse 
(
    Long userId,
    String fullName,
    String email,
    String token
) {
    
}
