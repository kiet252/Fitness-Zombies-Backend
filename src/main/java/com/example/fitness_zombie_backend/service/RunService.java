package com.example.fitness_zombie_backend.service;

import com.example.fitness_zombie_backend.dto.run.CreateRunRequest;
import com.example.fitness_zombie_backend.dto.run.RunCompletionResponse;
import com.example.fitness_zombie_backend.entity.Profile;
import com.example.fitness_zombie_backend.entity.Run;
import com.example.fitness_zombie_backend.repository.ProfileRepository;
import com.example.fitness_zombie_backend.repository.RunRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RunService {

    private final RunRepository runRepository;
    private final ProfileRepository profileRepository;
    private final AchievementService achievementService;

    public RunService(
            RunRepository runRepository,
            ProfileRepository profileRepository,
            AchievementService achievementService
    ) {
        this.runRepository = runRepository;
        this.profileRepository = profileRepository;
        this.achievementService = achievementService;
    }

    @Transactional
    public RunCompletionResponse createRun(
            UUID profileId,
            CreateRunRequest request
    ) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Profile not found")
                );

        if (!request.endTime().isAfter(request.startTime())) {
            throw new IllegalArgumentException(
                    "End time must be after start time"
            );
        }

        Run run = new Run();

        run.setProfile(profile);
        run.setType(request.type());
        run.setStartTime(request.startTime());
        run.setEndTime(request.endTime());
        run.setDistanceMeters(request.distanceMeters());
        run.setDurationSeconds(request.durationSeconds());
        run.setCaloriesBurned(request.caloriesBurned());

        run.setBestSpeedKmh(
                request.bestSpeedKmh() == null
                        ? 0.0f
                        : request.bestSpeedKmh()
        );

        run.setRouteData(request.routeData());

        int xpEarned = calculateRunXp(run);

        run.setXpEarned(xpEarned);

        Run savedRun = runRepository.save(run);

        addXp(profile, xpEarned);

        profileRepository.save(profile);

        java.util.List<com.example.fitness_zombie_backend.dto.achievement.ProfileAchievementDto> unlockedAchievements = 
                achievementService.evaluateAchievements(profile);

        return new RunCompletionResponse(
                savedRun,
                xpEarned,
                profile.getCurrentXp(),
                profile.getLevel(),
                unlockedAchievements
        );
    }

    @Transactional(readOnly = true)
    public List<Run> getAllRuns(UUID profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new IllegalArgumentException("Profile not found");
        }

        return runRepository
                .findAllByProfileIdOrderByStartTimeDesc(profileId);
    }
    
    private int calculateRunXp(Run run) {
        int baseXp = run.getDistanceMeters() / 100;

        if (run.getType().name().equalsIgnoreCase("zombie")) {
                baseXp = (int) (baseXp * 1.5);
        }

        return Math.max(baseXp, 1);
    }

    private void addXp(Profile profile, int xp) {

        int newXp = profile.getCurrentXp() + xp;

        profile.setCurrentXp(newXp);

        int newLevel = calculateLevel(newXp);

        profile.setLevel(newLevel);
    }

    private int calculateLevel(int xp) {
        return (xp / 500) + 1;
    }
}