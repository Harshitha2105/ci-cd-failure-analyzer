package com.example.cicd.repository;

import com.example.cicd.model.PipelineHealth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository
        extends JpaRepository<PipelineHealth, Long> {
}
