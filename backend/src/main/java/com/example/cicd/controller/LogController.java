package com.example.cicd.controller;

import com.example.cicd.dto.AnalysisResponse;
import com.example.cicd.dto.LogRequest;
import com.example.cicd.model.Ticket;
import com.example.cicd.service.LogService;
import com.example.cicd.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin
public class LogController {

    @Autowired
    private LogService logService;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/analyze")
    public AnalysisResponse analyzeLog(@RequestBody LogRequest request) {

        // 1️⃣ Analyze the log
        AnalysisResponse response = logService.processLog(request);

        // 2️⃣ Create ticket from analysis
        Ticket ticket = new Ticket();
        ticket.setPipelineName(request.getPipelineName());
        ticket.setFailureType(response.getFailureType());
        ticket.setRootCause(response.getRootCause());
        ticket.setStatus("OPEN");

        ticketService.saveTicket(ticket);

        // 3️⃣ Return analysis response
        return response;
    }
}
