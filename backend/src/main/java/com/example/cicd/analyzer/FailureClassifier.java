package com.example.cicd.analyzer;

import org.springframework.stereotype.Component;

@Component
public class FailureClassifier {

    public String classify(String error) {
        error = error.toLowerCase();

        if (error.contains("dependency") || error.contains("modulenotfound"))
            return "DEPENDENCY_ERROR";
        if (error.contains("test"))
            return "TEST_FAILURE";
        if (error.contains("permission"))
            return "PERMISSION_ERROR";
        if (error.contains("timeout"))
            return "TIMEOUT";

        return "BUILD_FAILURE";
    }
}
