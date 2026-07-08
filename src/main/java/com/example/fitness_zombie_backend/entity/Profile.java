package com.example.fitness_zombie_backend.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    private String email;

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}