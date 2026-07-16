package com.example.fitness_zombie_backend.service;

import com.example.fitness_zombie_backend.dto.achievement.ProfileAchievementDto;
import com.example.fitness_zombie_backend.entity.Profile;
import com.example.fitness_zombie_backend.entity.ProfileAchievement;
import com.example.fitness_zombie_backend.repository.ProfileAchievementRepository;
import com.example.fitness_zombie_backend.repository.RunRepository;
import com.example.fitness_zombie_backend.type.RunType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AchievementService {

    private final ProfileAchievementRepository profileAchievementRepository;
    private final RunRepository runRepository;

    public AchievementService(ProfileAchievementRepository profileAchievementRepository, RunRepository runRepository) {
        this.profileAchievementRepository = profileAchievementRepository;
        this.runRepository = runRepository;
    }

    @Transactional
    public List<ProfileAchievementDto> evaluateAchievements(Profile profile) {
        profileAchievementRepository.insertMissingAchievementsForProfile(profile.getId());

        List<ProfileAchievement> lockedAchievements = profileAchievementRepository.findLockedAchievementsByProfileId(profile.getId());
        if (lockedAchievements.isEmpty()) {
            return List.of();
        }

        int runCount = runRepository.countByProfileId(profile.getId());
        int totalDistance = runRepository.sumDistanceByProfileId(profile.getId());
        int zombieRunCount = runRepository.countByProfileIdAndType(profile.getId(), RunType.zombie);
        int level = profile.getLevel();

        List<ProfileAchievementDto> unlockedDtos = new ArrayList<>();

        for (ProfileAchievement pa : lockedAchievements) {
            String code = pa.getAchievement().getCode();
            boolean isMet = false;

            switch (code) {
                case "FIRST_RUN":
                    isMet = runCount >= 1;
                    break;
                case "TOTAL_DISTANCE_5K":
                    isMet = totalDistance >= 5000;
                    break;
                case "TOTAL_DISTANCE_21K":
                    isMet = totalDistance >= 21000;
                    break;
                case "TOTAL_DISTANCE_100K":
                    isMet = totalDistance >= 100000;
                    break;
                case "RUN_COUNT_10":
                    isMet = runCount >= 10;
                    break;
                case "RUN_COUNT_50":
                    isMet = runCount >= 50;
                    break;
                case "RUN_COUNT_100":
                    isMet = runCount >= 100;
                    break;
                case "ZOMBIE_FIRST":
                    isMet = zombieRunCount >= 1;
                    break;
                case "ZOMBIE_25":
                    isMet = zombieRunCount >= 25;
                    break;
                case "LEVEL_5":
                    isMet = level >= 5;
                    break;
                case "LEVEL_20":
                    isMet = level >= 20;
                    break;
            }

            if (isMet) {
                int updatedRows = profileAchievementRepository.unlockAchievement(profile.getId(), pa.getAchievement().getId());
                if (updatedRows > 0) {
                    pa.setUnlockedAt(OffsetDateTime.now());
                    unlockedDtos.add(mapToDto(pa));
                }
            }
        }
        return unlockedDtos;
    }

    @Transactional(readOnly = true)
    public List<ProfileAchievementDto> getProfileAchievements(UUID profileId) {
        List<ProfileAchievement> achievements = profileAchievementRepository.findByProfileId(profileId);
        return achievements.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ProfileAchievementDto mapToDto(ProfileAchievement pa) {
        ProfileAchievementDto dto = new ProfileAchievementDto();
        dto.setAchievementId(pa.getAchievement().getId());
        dto.setTitle(pa.getAchievement().getTitle());
        dto.setDescription(pa.getAchievement().getDescription());
        dto.setIcon(pa.getAchievement().getIcon());
        dto.setCategory(pa.getAchievement().getCategory());
        dto.setCode(pa.getAchievement().getCode());
        dto.setUnlockedAt(pa.getUnlockedAt());
        return dto;
    }
}
