package com.example.fitness_zombie_backend.dto.run;

import com.example.fitness_zombie_backend.entity.Run;

public record RunCompletionResponse(
        Run run,
        int xpEarned,
        int currentXp,
        int level
) {}