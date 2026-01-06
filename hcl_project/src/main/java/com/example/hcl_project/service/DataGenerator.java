package com.example.hcl_project.service;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.repository.ThreatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataGenerator {

    private final ThreatLogRepository repo;
    private final Random random = new Random();

    private final String[] levels = {"Critical","Warning","Safe"};

    @Scheduled(fixedRate = 5000)  // every 5 seconds
    public void generateThreat() {

        ThreatLog log = new ThreatLog();
        log.setSeverity(levels[random.nextInt(levels.length)]);
        log.setTime(LocalDateTime.now());

        repo.save(log);
    }
}
