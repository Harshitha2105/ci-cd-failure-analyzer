package com.example.cicd.analyzer;

import org.springframework.stereotype.Component;

@Component
public class LogParser {

    public String extractError(String log) {
        for (String line : log.split("\n")) {
            if (line.toLowerCase().contains("error") ||
                    line.toLowerCase().contains("failed")) {
                return line;
            }
        }
        return "UNKNOWN_ERROR";
    }
}
