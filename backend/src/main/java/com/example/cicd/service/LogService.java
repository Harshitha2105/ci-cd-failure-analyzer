package com.example.cicd.service;

import com.example.cicd.analyzer.FailureClassifier;
import com.example.cicd.analyzer.LogParser;
import com.example.cicd.analyzer.RecommendationEngine;
import com.example.cicd.analyzer.RootCauseAnalyzer;
import com.example.cicd.dto.AnalysisResponse;
import com.example.cicd.dto.LogRequest;
import com.example.cicd.dto.PipelineStatus;
import com.example.cicd.model.PipelineLog;
import com.example.cicd.repository.PipelineLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    @Autowired
    private LogParser parser;

    @Autowired
    private FailureClassifier classifier;

    @Autowired
    private RootCauseAnalyzer rootCauseAnalyzer;

    @Autowired
    private RecommendationEngine recommendationEngine;

    @Autowired
    private PipelineLogRepository repository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private AlertService alertService;

    // 🔥 WebSocket sender
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public AnalysisResponse processLog(LogRequest request) {

        String pipelineName = request.getPipelineName();
        String logContent = request.getLogContent();

        // 🔥 1️⃣ Send RUNNING status immediately
        sendPipelineUpdate(pipelineName, "RUNNING", logContent);

        String error = parser.extractError(logContent);

        String failureType = "SUCCESS";
        String rootCause = "NONE";
        String recommendation = "No action needed";
        String status = "SUCCESS";

        // 🔥 2️⃣ Detect failure
        if (error != null && !error.isEmpty()) {

            failureType = classifier.classify(error);
            rootCause = rootCauseAnalyzer.analyze(failureType);
            recommendation = recommendationEngine.recommend(failureType);
            status = "FAILED";

            // Auto ticket creation
            ticketService.createTicket(
                    pipelineName,
                    failureType,
                    rootCause
            );

            // Alert
            alertService.sendAlert(
                    "Pipeline Failed: " + failureType
            );
        }

        // 🔥 3️⃣ Save log in database
        PipelineLog log = new PipelineLog();
        log.setPipelineName(pipelineName);
        log.setStatus(status);
        log.setFailureType(failureType);
        log.setLogContent(logContent);
        log.setTimestamp(LocalDateTime.now());

        repository.save(log);

        // 🔥 4️⃣ IMPORTANT: Send final status + logs AFTER saving
        sendPipelineUpdate(pipelineName, status, logContent);

        // 🔥 5️⃣ Return analysis result
        return new AnalysisResponse(
                failureType,
                rootCause,
                recommendation
        );
    }

    // 🔥 WebSocket update method
    private void sendPipelineUpdate(
            String pipeline,
            String status,
            String log
    ) {
        PipelineStatus update =
                new PipelineStatus(pipeline, status, log);

        messagingTemplate.convertAndSend(
                "/topic/pipeline",
                update
        );
    }
}