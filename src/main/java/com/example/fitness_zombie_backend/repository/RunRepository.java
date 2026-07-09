package com.example.fitness_zombie_backend.repository;

import com.example.fitness_zombie_backend.entity.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface RunRepository extends JpaRepository<Run, UUID> {

    @Query("""
        SELECT r
        FROM Run r
        WHERE r.profile.id = :profileId
        AND r.startTime >= :start
        ORDER BY r.startTime ASC
    """)
    List<Run> findRunsFromDate(UUID profileId, OffsetDateTime start);
}