package com.example.cicd.service;

import com.example.cicd.analyzer.FailureClassifier;
import com.example.cicd.analyzer.LogParser;
import com.example.cicd.analyzer.RecommendationEngine;
import com.example.cicd.analyzer.RootCauseAnalyzer;
import com.example.cicd.dto.AnalysisResponse;
import com.example.cicd.dto.LogRequest;
import com.example.cicd.model.PipelineLog;
import com.example.cicd.repository.PipelineLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public AnalysisResponse processLog(LogRequest request) {

        String error = parser.extractError(request.getLogContent());
        String failureType = classifier.classify(error);
        String rootCause = rootCauseAnalyzer.analyze(failureType);
        String recommendation = recommendationEngine.recommend(failureType);

        PipelineLog log = new PipelineLog();
        log.setPipelineName(request.getPipelineName());
        log.setStatus("FAILED");
        log.setFailureType(failureType);
        log.setLogContent(request.getLogContent());
        log.setTimestamp(LocalDateTime.now());

        repository.save(log);

        ticketService.createTicket(
                request.getPipelineName(),
                failureType,
                rootCause
        );

        alertService.sendAlert("Pipeline Failed: " + failureType);

        return new AnalysisResponse(failureType, rootCause, recommendation);
    }
}
