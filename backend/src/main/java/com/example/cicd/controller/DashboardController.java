package com.example.cicd.controller;

import com.example.cicd.dto.PredictionResponse;
import com.example.cicd.service.FailurePredictionService;
import com.example.cicd.service.HealthScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private FailurePredictionService predictionService;

    @Autowired
    private HealthScoreService healthService;

    @GetMapping("/prediction")
    public PredictionResponse getPrediction() {
        return predictionService.predict();
    }

    @GetMapping("/health")
    public int getHealthScore() {
        return healthService.calculateHealthScore();
    }
}
