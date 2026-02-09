package com.example.cicd.service;

import com.example.cicd.model.Ticket;
import com.example.cicd.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    // 🔹 Used by LogController
    public Ticket saveTicket(Ticket ticket) {
        return repository.save(ticket);
    }

    // 🔹 Can still be used elsewhere
    public void createTicket(String pipeline, String failure, String cause) {
        Ticket t = new Ticket();
        t.setPipelineName(pipeline);
        t.setFailureType(failure);
        t.setRootCause(cause);
        t.setStatus("OPEN");
        repository.save(t);
    }

    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }
}
