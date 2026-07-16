package com.example.fitness_zombie_backend.repository;

import com.example.fitness_zombie_backend.entity.ProfileAchievement;
import com.example.fitness_zombie_backend.entity.ProfileAchievementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfileAchievementRepository extends JpaRepository<ProfileAchievement, ProfileAchievementId> {
    List<ProfileAchievement> findByProfileId(UUID profileId);

    @Modifying
    @Query(value = "INSERT INTO profiles_achievements (profiles_id, achievement_id, unlocked_at) " +
                   "SELECT :profileId, a.id, null FROM achievements a " +
                   "ON CONFLICT (profiles_id, achievement_id) DO NOTHING", nativeQuery = true)
    void insertMissingAchievementsForProfile(@Param("profileId") UUID profileId);

    @Query("SELECT pa FROM ProfileAchievement pa JOIN FETCH pa.achievement WHERE pa.id.profilesId = :profileId AND pa.unlockedAt IS NULL")
    List<ProfileAchievement> findLockedAchievementsByProfileId(@Param("profileId") UUID profileId);

    @Modifying
    @Query("UPDATE ProfileAchievement pa SET pa.unlockedAt = CURRENT_TIMESTAMP WHERE pa.id.profilesId = :profileId AND pa.id.achievementId = :achievementId AND pa.unlockedAt IS NULL")
    int unlockAchievement(@Param("profileId") UUID profileId, @Param("achievementId") UUID achievementId);
}
