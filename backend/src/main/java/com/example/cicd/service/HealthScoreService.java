package com.example.cicd.service;

import com.example.cicd.repository.PipelineLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthScoreService {

    @Autowired
    private PipelineLogRepository repository;

    public int calculateHealthScore() {
        long total = repository.count();
        long success = repository.findAll()
                .stream()
                .filter(l -> "SUCCESS".equals(l.getStatus()))
                .count();

        return total == 0 ? 100 : (int) ((success * 100) / total);
    }
}

