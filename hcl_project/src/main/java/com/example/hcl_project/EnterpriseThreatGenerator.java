package com.example.hcl_project;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.repository.ThreatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class EnterpriseThreatGenerator {

    private final ThreatLogRepository repo;
    Random r = new Random();

    String[] categories = {"LOGIN","API","MALWARE","NETWORK"};

    String[] apiAttacks = {
            "SQL Injection",
            "Broken Auth Attack",
            "JWT Token Abuse",
            "API Flood",
            "Webhook Exploit"
    };

    String[] loginEvents = {
            "Failed Admin Login",
            "Brute Force Attempt",
            "Multiple Account Lock",
            "Suspicious Geo Login"
    };

    String[] malwareEvents = {
            "Ransomware Detected",
            "Trojan Upload",
            "Keylogger Activity",
            "Remote Access Malware"
    };

    String[] networkEvents = {
            "Port Scan Detected",
            "DDoS Packet Flood",
            "Suspicious Outbound Traffic"
    };

    String[] severities = {"CRITICAL","WARNING","SAFE"};

    String[] devices = {"Firewall-01","API-Gateway","DB-Server","Auth-Server","LinuxNode-7"};

    String randomIp() {
        return r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255)+"."+r.nextInt(255);
    }

    String pick(String[] arr){
        return arr[r.nextInt(arr.length)];
    }

    // Runs every 3 seconds — like real logs flowing
    @Scheduled(fixedRate = 3000)
    public void generate() {

        ThreatLog t = new ThreatLog();

        String category = pick(categories);
        t.setCategory(category);

        switch (category){

            case "API":
                t.setThreatType(pick(apiAttacks));
                break;

            case "LOGIN":
                t.setThreatType(pick(loginEvents));
                break;

            case "MALWARE":
                t.setThreatType(pick(malwareEvents));
                break;

            case "NETWORK":
                t.setThreatType(pick(networkEvents));
                break;
        }

        t.setSeverity(pick(severities));
        t.setSourceIp(randomIp());
        t.setDevice(pick(devices));
        t.setTime(LocalDateTime.now());

        repo.save(t);
    }
}
