package com.example.fitness_zombie_backend.repository;

import com.example.fitness_zombie_backend.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByEmail(String email);

}