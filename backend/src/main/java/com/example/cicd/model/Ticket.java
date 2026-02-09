package com.example.cicd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    private String pipelineName;
    private String failureType;
    private String rootCause;
    private String status;
}
