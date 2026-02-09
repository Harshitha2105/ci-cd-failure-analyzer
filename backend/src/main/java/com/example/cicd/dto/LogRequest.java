package com.example.cicd.dto;

public class LogRequest {

    private String pipelineName;
    private String logContent;

    // No-args constructor (important for Spring/Jackson)
    public LogRequest() {
    }

    // Optional all-args constructor
    public LogRequest(String pipelineName, String logContent) {
        this.pipelineName = pipelineName;
        this.logContent = logContent;
    }

    // Getter for pipelineName
    public String getPipelineName() {
        return pipelineName;
    }

    // Setter for pipelineName
    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    // Getter for logContent
    public String getLogContent() {
        return logContent;
    }

    // Setter for logContent
    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}
