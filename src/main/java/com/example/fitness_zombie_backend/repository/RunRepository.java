package com.example.fitness_zombie_backend.repository;

import com.example.fitness_zombie_backend.entity.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    List<Run> findRunsFromDate(
            @Param("profileId") UUID profileId,
            @Param("start") OffsetDateTime start
    );

    List<Run> findAllByProfileIdOrderByStartTimeDesc(UUID profileId);

    int countByProfileId(UUID profileId);

    int countByProfileIdAndType(UUID profileId, com.example.fitness_zombie_backend.type.RunType type);

    @Query("SELECT COALESCE(SUM(r.distanceMeters), 0) FROM Run r WHERE r.profile.id = :profileId")
    int sumDistanceByProfileId(@Param("profileId") UUID profileId);
}