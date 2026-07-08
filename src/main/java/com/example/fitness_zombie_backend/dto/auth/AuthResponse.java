package com.example.fitness_zombie_backend.dto.auth;

import java.util.UUID;

public record AuthResponse 
(
    UUID userId,
    String fullName,
    String email,
    String token
) {
    
}
