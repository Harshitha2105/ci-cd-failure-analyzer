package com.example.cicd.repository;

import com.example.cicd.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository
        extends JpaRepository<Ticket, Long> {
}
