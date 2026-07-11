package com.example.fitness_zombie_backend.service;

import com.example.fitness_zombie_backend.dto.run.CreateRunRequest;
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

    public RunService(
            RunRepository runRepository,
            ProfileRepository profileRepository
    ) {
        this.runRepository = runRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public Run createRun(
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

        return runRepository.save(run);
    }

    @Transactional(readOnly = true)
    public List<Run> getAllRuns(UUID profileId) {
        if (!profileRepository.existsById(profileId)) {
            throw new IllegalArgumentException("Profile not found");
        }

        return runRepository
                .findAllByProfileIdOrderByStartTimeDesc(profileId);
    }
}