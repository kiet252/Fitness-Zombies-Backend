package com.example.fitness_zombie_backend.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "runs")
public class Run {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "profiles_id", nullable = false)
    private Profile profile;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "distance_meters")
    private int distanceMeters;

    @Column(name = "duration_seconds")
    private int durationSeconds;

    @Column(name = "calories_burned")
    private int caloriesBurned;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public int getDistanceMeters() {
        return distanceMeters;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }
}