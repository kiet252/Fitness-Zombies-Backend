package com.example.fitness_zombie_backend.service;

import com.example.fitness_zombie_backend.dto.home.HomeResponse;
import com.example.fitness_zombie_backend.entity.DailyStats;
import com.example.fitness_zombie_backend.entity.Profile;
import com.example.fitness_zombie_backend.entity.Run;
import com.example.fitness_zombie_backend.repository.DailyStatsRepository;
import com.example.fitness_zombie_backend.repository.ProfileRepository;
import com.example.fitness_zombie_backend.repository.RunRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HomeService {

    private final ProfileRepository profileRepository;
    private final DailyStatsRepository dailyStatsRepository;
    private final RunRepository runRepository;

    public HomeService(
            ProfileRepository profileRepository,
            DailyStatsRepository dailyStatsRepository,
            RunRepository runRepository
    ) {
        this.profileRepository = profileRepository;
        this.dailyStatsRepository = dailyStatsRepository;
        this.runRepository = runRepository;
    }

    public HomeResponse getHome(UUID profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        DailyStats today = dailyStatsRepository
                .findByProfileIdAndRecordDate(profileId, LocalDate.now())
                .orElse(null);

        LocalDate monday = LocalDate.now().with(DayOfWeek.MONDAY);
        OffsetDateTime startOfWeek = monday.atStartOfDay().atOffset(OffsetDateTime.now().getOffset());

        List<Run> weeklyRuns = runRepository.findRunsFromDate(profileId, startOfWeek);

        List<Double> weeklyDistance = buildWeeklyDistance(weeklyRuns);

        return new HomeResponse(
                new HomeResponse.ProfileDto(
                        profile.getId(),
                        profile.getFullName(),
                        profile.getEmail(),
                        profile.getLevel(),
                        profile.getCurrentXp(),
                        profile.getDailyStepGoal()
                ),
                new HomeResponse.DailyStatsDto(
                        today == null ? 0 : today.getSteps(),
                        today == null ? 0 : today.getDistanceMeters(),
                        today == null ? 0 : today.getCaloriesBurned(),
                        today == null ? 0 : today.getActiveSeconds()
                ),
                weeklyDistance,
                List.of()
        );
    }

    private List<Double> buildWeeklyDistance(List<Run> runs) {
        List<Double> distances = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

        for (Run run : runs) {
            int dayIndex = run.getStartTime().getDayOfWeek().getValue() - 1;
            double km = run.getDistanceMeters() / 1000.0;
            distances.set(dayIndex, distances.get(dayIndex) + km);
        }

        return distances;
    }
}