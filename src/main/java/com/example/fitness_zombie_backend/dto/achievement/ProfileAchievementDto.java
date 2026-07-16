package com.example.fitness_zombie_backend.dto.achievement;

import com.example.fitness_zombie_backend.type.AchievementCategory;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProfileAchievementDto {
    private UUID achievementId;
    private String title;
    private String description;
    private String icon;
    private AchievementCategory category;
    private String code;
    private OffsetDateTime unlockedAt;

    public UUID getAchievementId() { return achievementId; }
    public void setAchievementId(UUID achievementId) { this.achievementId = achievementId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public AchievementCategory getCategory() { return category; }
    public void setCategory(AchievementCategory category) { this.category = category; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public OffsetDateTime getUnlockedAt() { return unlockedAt; }
    public void setUnlockedAt(OffsetDateTime unlockedAt) { this.unlockedAt = unlockedAt; }
}
