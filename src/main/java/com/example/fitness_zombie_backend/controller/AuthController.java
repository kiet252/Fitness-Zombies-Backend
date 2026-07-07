package com.example.fitness_zombie_backend.controller;

import com.example.fitness_zombie_backend.dto.auth.AuthResponse;
import com.example.fitness_zombie_backend.dto.auth.LoginRequest;
import com.example.fitness_zombie_backend.dto.auth.RegisterRequest;
import com.example.fitness_zombie_backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}