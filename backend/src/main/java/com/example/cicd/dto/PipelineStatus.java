package com.example.cicd.dto;

public class PipelineStatus {

    private String pipeline;
    private String status;
    private String log;

    // Constructor
    public PipelineStatus(String pipeline, String status, String log) {
        this.pipeline = pipeline;
        this.status = status;
        this.log = log;
    }

    // Getters
    public String getPipeline() {
        return pipeline;
    }

    public String getStatus() {
        return status;
    }

    public String getLog() {
        return log;
    }

    // Setters
    public void setPipeline(String pipeline) {
        this.pipeline = pipeline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLog(String log) {
        this.log = log;
    }
}