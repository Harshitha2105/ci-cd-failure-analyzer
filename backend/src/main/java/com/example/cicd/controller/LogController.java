package com.example.cicd.controller;

import com.example.cicd.dto.AnalysisResponse;
import com.example.cicd.dto.LogRequest;
import com.example.cicd.dto.PredictionResponse;
import com.example.cicd.model.Ticket;
import com.example.cicd.service.FailurePredictionService;
import com.example.cicd.service.LogService;
import com.example.cicd.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin("*")
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FailurePredictionService predictionService;

    // 🔥 Analyze uploaded pipeline log
    @PostMapping("/analyze")
    public AnalysisResponse analyzeLog(@RequestBody LogRequest request) {

        // 1️⃣ Analyze the log
        AnalysisResponse response = logService.processLog(request);

        // 2️⃣ Create ticket automatically
        Ticket ticket = new Ticket();
        ticket.setPipelineName(request.getPipelineName());
        ticket.setFailureType(response.getFailureType());
        ticket.setRootCause(response.getRootCause());
        ticket.setStatus("OPEN");

        ticketService.saveTicket(ticket);

        // 3️⃣ Return analysis result
        return response;
    }

    // 🔥 Get prediction for specific pipeline
    @GetMapping("/predict/{pipelineName}")
    public PredictionResponse predictPipeline(
            @PathVariable String pipelineName) {

        return predictionService.predict(pipelineName);
    }
}