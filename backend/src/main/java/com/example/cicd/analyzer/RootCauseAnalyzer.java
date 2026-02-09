package com.example.cicd.analyzer;

import org.springframework.stereotype.Component;

@Component
public class RootCauseAnalyzer {

    public String analyze(String failureType) {
        return switch (failureType) {
            case "DEPENDENCY_ERROR" -> "Missing dependency";
            case "TEST_FAILURE" -> "Failing unit test";
            case "PERMISSION_ERROR" -> "Permission issue";
            case "TIMEOUT" -> "Pipeline execution timeout";
            default -> "Build or configuration error";
        };
    }
}
