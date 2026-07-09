package com.example.fitness_zombie_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "daily_stats")
public class DailyStats {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "profiles_id", nullable = false)
    private Profile profile;

    @Column(name = "record_date")
    private LocalDate recordDate;

    private int steps;

    @Column(name = "distance_meters")
    private int distanceMeters;

    @Column(name = "calories_burned")
    private int caloriesBurned;

    @Column(name = "active_seconds")
    private int activeSeconds;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public int getSteps() {
        return steps;
    }

    public int getDistanceMeters() {
        return distanceMeters;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public int getActiveSeconds() {
        return activeSeconds;
    }
}