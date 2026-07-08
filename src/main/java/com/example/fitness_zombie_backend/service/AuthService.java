package com.example.fitness_zombie_backend.service;

import com.example.fitness_zombie_backend.dto.auth.AuthResponse;
import com.example.fitness_zombie_backend.dto.auth.LoginRequest;
import com.example.fitness_zombie_backend.dto.auth.RegisterRequest;
import com.example.fitness_zombie_backend.entity.Profile;
import com.example.fitness_zombie_backend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class AuthService {

    private final ProfileRepository profileRepository;
    private final RestClient restClient;

    public AuthService(
            ProfileRepository profileRepository,
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.publishable-key}") String publishableKey
    ) {
        this.profileRepository = profileRepository;
        this.restClient = RestClient.builder()
                .baseUrl(supabaseUrl)
                .defaultHeader("apikey", publishableKey)
                .defaultHeader("Authorization", "Bearer " + publishableKey)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Map<String, Object> body = Map.of(
                "email", request.email(),
                "password", request.password(),
                "data", Map.of(
                        "full_name", request.fullName()
                )
        );

        Map response = restClient.post()
                .uri("/auth/v1/signup")
                .body(body)
                .retrieve()
                .body(Map.class);

        String token = response.get("access_token") == null
                ? null
                : response.get("access_token").toString();

        Profile profile = profileRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Profile was not created yet"));

        return new AuthResponse(
                profile.getId(),
                profile.getFullName(),
                profile.getEmail(),
                token
        );
    }

    public AuthResponse login(LoginRequest request) {
        Map<String, Object> body = Map.of(
                "email", request.email(),
                "password", request.password()
        );

        Map response = restClient.post()
                .uri("/auth/v1/token?grant_type=password")
                .body(body)
                .retrieve()
                .body(Map.class);

        String token = response.get("access_token").toString();

        Profile profile = profileRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return new AuthResponse(
                profile.getId(),
                profile.getFullName(),
                profile.getEmail(),
                token
        );
    }
}