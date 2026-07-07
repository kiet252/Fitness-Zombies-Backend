package com.example.fitness_zombie_backend.service;

import com.example.fitness_zombie_backend.dto.auth.AuthResponse;
import com.example.fitness_zombie_backend.dto.auth.LoginRequest;
import com.example.fitness_zombie_backend.dto.auth.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponse login(LoginRequest request) {
        if (
                "test@test.com".equals(request.email())
                        && "123456".equals(request.password())
        ) {
            return new AuthResponse(
                    1L,
                    "Test User",
                    "test@test.com",
                    "fake-jwt-token"
            );
        }

        throw new RuntimeException("Invalid email or password");
    }

    public AuthResponse register(RegisterRequest request) {
        if (
                request.fullName() != null && !request.fullName().isBlank()
                        && request.email() != null && !request.email().isBlank()
                        && request.password() != null && !request.password().isBlank()
        ) {
            return new AuthResponse(
                    1L,
                    request.fullName(),
                    request.email(),
                    "fake-jwt-token"
            );
        }

        throw new RuntimeException("Invalid registration information");
    }
}