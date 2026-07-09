package com.example.fitness_zombie_backend.dto.home;

import java.util.List;
import java.util.UUID;

public record HomeResponse(
        ProfileDto profile,
        DailyStatsDto today,
        List<Double> weeklyDistance,
        List<AchievementDto> recentAchievements
) {
    public record ProfileDto(
            UUID id,
            String fullName,
            String email,
            int level,
            int currentXp,
            int dailyStepGoal
    ) {}

    public record DailyStatsDto(
            int steps,
            int distanceMeters,
            int caloriesBurned,
            int activeSeconds
    ) {}

    public record AchievementDto(
            String title,
            String icon
    ) {}
}