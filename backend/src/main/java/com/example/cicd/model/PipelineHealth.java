package com.example.cicd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PipelineHealth {

    @Id
    @GeneratedValue
    private Long id;

    private int healthScore;
}
