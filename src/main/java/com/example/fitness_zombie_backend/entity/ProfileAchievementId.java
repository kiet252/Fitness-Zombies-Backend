package com.example.fitness_zombie_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ProfileAchievementId implements Serializable {

    @Column(name = "profiles_id")
    private UUID profilesId;

    @Column(name = "achievement_id")
    private UUID achievementId;

    public ProfileAchievementId() {}

    public ProfileAchievementId(UUID profilesId, UUID achievementId) {
        this.profilesId = profilesId;
        this.achievementId = achievementId;
    }

    public UUID getProfilesId() { return profilesId; }
    public void setProfilesId(UUID profilesId) { this.profilesId = profilesId; }

    public UUID getAchievementId() { return achievementId; }
    public void setAchievementId(UUID achievementId) { this.achievementId = achievementId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileAchievementId that = (ProfileAchievementId) o;
        return Objects.equals(profilesId, that.profilesId) && Objects.equals(achievementId, that.achievementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profilesId, achievementId);
    }
}
