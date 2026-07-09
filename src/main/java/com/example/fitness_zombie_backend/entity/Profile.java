package com.example.fitness_zombie_backend.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "current_xp")
    private int currentXp;

    @Column(name = "level")
    private int level;

    @Column(name = "daily_step_goal")
    private int dailyStepGoal;

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public int getLevel() {
        return level;
    }

    public int getDailyStepGoal() {
        return dailyStepGoal;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDailyStepGoal(int dailyStepGoal) {
        this.dailyStepGoal = dailyStepGoal;
    }
}