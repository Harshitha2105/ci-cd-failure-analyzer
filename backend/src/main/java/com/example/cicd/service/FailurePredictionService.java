package com.example.cicd.service;

import com.example.cicd.dto.PredictionResponse;
import com.example.cicd.model.PipelineLog;
import com.example.cicd.repository.PipelineLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FailurePredictionService {

    @Autowired
    private PipelineLogRepository repository;

    // 🔥 UPDATED METHOD (Pipeline-specific + Pattern Detection)
    public PredictionResponse predict(String pipelineName) {

        List<PipelineLog> logs = repository.findByPipelineName(pipelineName);

        long total = logs.size();

        if (total == 0) {
            return new PredictionResponse(0.0, "LOW", "NONE");
        }

        // 🔹 Failed runs
        long failed = logs.stream()
                .filter(l -> !"SUCCESS".equalsIgnoreCase(l.getStatus()))
                .count();

        // 🔹 Failure probability
        double probability = (double) failed / total;

        // 🔹 Risk level
        String risk;
        if (probability >= 0.70) {
            risk = "HIGH";
        } else if (probability >= 0.40) {
            risk = "MEDIUM";
        } else {
            risk = "LOW";
        }

        // 🔥 Pattern Detection: Most frequent failure type
        Map<String, Long> failureMap = logs.stream()
                .filter(l -> l.getFailureType() != null)
                .filter(l -> !"SUCCESS".equalsIgnoreCase(l.getStatus()))
                .collect(Collectors.groupingBy(
                        l -> l.getFailureType().toUpperCase(),
                        Collectors.counting()
                ));

        String predictedFailure = failureMap.entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("NONE");

        // 🔥 Consecutive Failure Detection (bonus logic)
        long recentFailures = logs.stream()
                .skip(Math.max(0, total - 3))
                .filter(l -> !"SUCCESS".equalsIgnoreCase(l.getStatus()))
                .count();

        if (recentFailures == 3) {
            risk = "HIGH";
        }

        return new PredictionResponse(probability, risk, predictedFailure);
    }
}