package com.example.hcl_project;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.repository.ThreatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ThreatLogRepository repo;

    @Override
    public void run(String... args) {

        for(int i=0;i<20;i++){
            ThreatLog t = new ThreatLog();
            t.setSeverity(i % 3 == 0 ? "CRITICAL" :
                    i % 3 == 1 ? "WARNING" :
                            "SAFE");
            t.setTime(LocalDateTime.now().minusSeconds(i * 5));
            repo.save(t);
        }

        System.out.println("Dummy Threat Data Loaded 👍");
    }
}
