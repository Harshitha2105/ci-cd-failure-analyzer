package com.example.cicd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalysisResponse {
    private String failureType;
    private String rootCause;
    private String recommendation;
}

