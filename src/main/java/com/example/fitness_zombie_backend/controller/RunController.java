package com.example.fitness_zombie_backend.controller;

import com.example.fitness_zombie_backend.dto.run.CreateRunRequest;
import com.example.fitness_zombie_backend.entity.Run;
import com.example.fitness_zombie_backend.service.RunService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/run")
public class RunController {

    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Run createRun(
            @RequestHeader("X-USER-ID") UUID userId,
            @Valid @RequestBody CreateRunRequest request
    ) {
        return runService.createRun(userId, request);
    }
}