package com.example.cicd.service;

import com.example.cicd.dto.PredictionResponse;
import com.example.cicd.repository.PipelineLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FailurePredictionService {

    @Autowired
    private PipelineLogRepository repository;

    public PredictionResponse predict() {
        long total = repository.count();
        long failed = repository.findAll()
                .stream()
                .filter(l -> !"SUCCESS".equals(l.getStatus()))
                .count();

        double probability = total == 0 ? 0 : (double) failed / total;
        String risk = probability > 0.6 ? "HIGH" : "LOW";

        return new PredictionResponse(probability, risk);
    }
}
