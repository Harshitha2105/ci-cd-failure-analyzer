package com.example.cicd.dto;

public class PredictionResponse {

    private double probability;
    private String risk;
    private String predictedFailure;

    // 🔥 Updated constructor
    public PredictionResponse(double probability, String risk, String predictedFailure) {
        this.probability = probability;
        this.risk = risk;
        this.predictedFailure = predictedFailure;
    }

    public double getProbability() {
        return probability;
    }

    public String getRisk() {
        return risk;
    }

    public String getPredictedFailure() {
        return predictedFailure;
    }
}