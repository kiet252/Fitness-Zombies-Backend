package com.example.fitness_zombie_backend.dto.run;

import com.example.fitness_zombie_backend.entity.Run;
import com.example.fitness_zombie_backend.dto.achievement.ProfileAchievementDto;
import java.util.List;

public record RunCompletionResponse(
        Run run,
        int xpEarned,
        int currentXp,
        int level,
        List<ProfileAchievementDto> newlyUnlockedAchievements
) {}