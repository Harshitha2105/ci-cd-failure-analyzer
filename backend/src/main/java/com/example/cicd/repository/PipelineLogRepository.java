package com.example.cicd.repository;

import com.example.cicd.model.PipelineLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PipelineLogRepository
        extends JpaRepository<PipelineLog, Long> {


    List<PipelineLog> findByPipelineName(String pipelineName);
}
