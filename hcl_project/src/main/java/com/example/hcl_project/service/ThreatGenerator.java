package com.example.hcl_project.service;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.repository.ThreatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ThreatGenerator {

    private final ThreatLogRepository repo;
    private final Random random = new Random();

    private final List<String> categories = List.of(
            "LOGIN", "API", "MALWARE", "NETWORK"
    );

    private final List<String> loginThreats = List.of(
            "Multiple Failed Login",
            "Brute Force Attempt",
            "Suspicious Admin Login"
    );

    private final List<String> apiThreats = List.of(
            "API Abuse Detected",
            "Token Tampering",
            "Rate Limit Attack"
    );

    private final List<String> malwareThreats = List.of(
            "Malware Signature Found",
            "Trojan Download Blocked",
            "Ransomware Activity"
    );

    private final List<String> networkThreats = List.of(
            "DDoS Spike Detected",
            "Unusual Port Scan",
            "Unknown Device Connected"
    );

    private final List<String> devices = List.of(
            "Windows Server",
            "Linux Server",
            "Cloud VM",
            "User Laptop"
    );

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void createThreat() {

        ThreatLog t = new ThreatLog();

        // ================= CATEGORY =================
        String category = categories.get(random.nextInt(categories.size()));
        t.setCategory(category);

        // ================= THREAT TYPE =================
        switch (category) {
            case "LOGIN" ->
                    t.setThreatType(loginThreats.get(random.nextInt(loginThreats.size())));
            case "API" ->
                    t.setThreatType(apiThreats.get(random.nextInt(apiThreats.size())));
            case "MALWARE" ->
                    t.setThreatType(malwareThreats.get(random.nextInt(malwareThreats.size())));
            default ->
                    t.setThreatType(networkThreats.get(random.nextInt(networkThreats.size())));
        }

        // ================= SEVERITY =================
        int sev = random.nextInt(100);
        if (sev < 20) t.setSeverity("CRITICAL");
        else if (sev < 50) t.setSeverity("WARNING");
        else t.setSeverity("SAFE");

        // ================= META DATA =================
        t.setSourceIp(
                random.nextInt(223) + "." +
                        random.nextInt(255) + "." +
                        random.nextInt(255) + "." +
                        random.nextInt(255)
        );

        t.setDevice(devices.get(random.nextInt(devices.size())));
        t.setTime(LocalDateTime.now());

        repo.save(t);

        System.out.println("🔥 Threat Generated → " +
                t.getCategory() + " | " +
                t.getThreatType() + " | " +
                t.getSeverity());
    }
}
