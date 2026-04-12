package com.example.cicd.controller;

import com.example.cicd.dto.PredictionResponse;
import com.example.cicd.service.FailurePredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
@CrossOrigin("*")
public class PredictionController {

    @Autowired
    private FailurePredictionService predictionService;

    // 🔥 Pipeline-specific prediction
    @GetMapping("/{pipelineName}")
    public PredictionResponse predict(
            @PathVariable String pipelineName) {

        return predictionService.predict(pipelineName);
    }
}