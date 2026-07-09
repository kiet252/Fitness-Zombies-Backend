package com.example.fitness_zombie_backend.controller;

import com.example.fitness_zombie_backend.dto.home.HomeResponse;
import com.example.fitness_zombie_backend.service.HomeService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/me")
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public HomeResponse getHome(@RequestHeader("X-USER-ID") UUID userId) {
        return homeService.getHome(userId);
    }
}
