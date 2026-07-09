package com.example.fitness_zombie_backend.repository;

import com.example.fitness_zombie_backend.entity.DailyStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface DailyStatsRepository extends JpaRepository<DailyStats, UUID> {
    Optional<DailyStats> findByProfileIdAndRecordDate(UUID profileId, LocalDate recordDate);
}