package com.example.cicd.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class PipelineLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pipelineName;
    private String status;
    private String failureType;

    @Column(length = 10000)
    private String logContent;

    private LocalDateTime timestamp;
}
