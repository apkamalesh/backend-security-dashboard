package com.example.hcl_project.controller;

import com.example.hcl_project.model.AlertMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequiredArgsConstructor
public class AlertSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    // Backend PUSH alerts anytime
    @PostMapping("/push-alert")
    public void sendAlert(@RequestBody String title) {
        AlertMessage alert = new AlertMessage(title, LocalTime.now().toString());
        messagingTemplate.convertAndSend("/topic/alerts", alert);
    }

    // If frontend wants to send something to server
    @MessageMapping("/send")
    @SendTo("/topic/alerts")
    public AlertMessage receive(String msg) {
        return new AlertMessage(msg, LocalTime.now().toString());
    }
}
