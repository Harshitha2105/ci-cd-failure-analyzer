package com.example.cicd.analyzer;

import org.springframework.stereotype.Component;

@Component
public class RecommendationEngine {

    public String recommend(String failureType) {
        return switch (failureType) {
            case "DEPENDENCY_ERROR" -> "Add dependency in pom.xml";
            case "TEST_FAILURE" -> "Fix failing test cases";
            case "PERMISSION_ERROR" -> "Update file permissions";
            case "TIMEOUT" -> "Increase pipeline timeout";
            default -> "Check build configuration";
        };
    }
}
