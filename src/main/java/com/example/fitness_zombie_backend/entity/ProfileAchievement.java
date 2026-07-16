package com.example.fitness_zombie_backend.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "profiles_achievements")
public class ProfileAchievement {

    @EmbeddedId
    private ProfileAchievementId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("profilesId")
    @JoinColumn(name = "profiles_id")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("achievementId")
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @Column(name = "unlocked_at", nullable = false)
    private OffsetDateTime unlockedAt = OffsetDateTime.now();

    public ProfileAchievementId getId() { return id; }
    public void setId(ProfileAchievementId id) { this.id = id; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public Achievement getAchievement() { return achievement; }
    public void setAchievement(Achievement achievement) { this.achievement = achievement; }

    public OffsetDateTime getUnlockedAt() { return unlockedAt; }
    public void setUnlockedAt(OffsetDateTime unlockedAt) { this.unlockedAt = unlockedAt; }
}
