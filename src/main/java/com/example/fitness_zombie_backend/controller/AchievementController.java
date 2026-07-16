package com.example.fitness_zombie_backend.controller;

import com.example.fitness_zombie_backend.dto.achievement.ProfileAchievementDto;
import com.example.fitness_zombie_backend.service.AchievementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileAchievementDto>> getProfileAchievements(@RequestHeader("X-USER-ID") UUID userId) {
        return ResponseEntity.ok(achievementService.getProfileAchievements(userId));
    }
}
