package com.example.cicd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketDTO {
    private String pipeline;
    private String issue;
    private String status;
}
