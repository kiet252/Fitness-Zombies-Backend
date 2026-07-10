package com.example.fitness_zombie_backend.dto.run;

import com.example.fitness_zombie_backend.type.RunType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.Map;

public record CreateRunRequest(

        @NotNull
        RunType type,

        @NotNull
        OffsetDateTime startTime,

        @NotNull
        OffsetDateTime endTime,

        @Min(0)
        Integer distanceMeters,

        @Min(0)
        Integer durationSeconds,

        @Min(0)
        Integer caloriesBurned,

        Map<String, Object> routeData
) {
}