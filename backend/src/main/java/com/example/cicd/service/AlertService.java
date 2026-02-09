package com.example.cicd.service;

import org.springframework.stereotype.Service;

@Service
public class AlertService {

    public void sendAlert(String message) {
        System.out.println("ALERT SENT: " + message);
    }
}
