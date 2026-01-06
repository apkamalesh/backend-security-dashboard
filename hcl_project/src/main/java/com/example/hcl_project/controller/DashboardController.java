package com.example.hcl_project.controller;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping("/stats")
    public Map<String,Integer> stats() {
        return service.getTopStats();
    }

    @GetMapping("/recent-alerts")
    public List<ThreatLog> recentAlerts() {
        return service.getRecentAlerts();
    }

    @GetMapping("/realtime")
    public List<Integer> realtime() {
        return service.getRealtimeThreats();
    }

    @GetMapping("/daily-activity")
    public Map<String,Integer> dailyActivity() {
        return service.getDailyActivity();
    }

    @GetMapping("/today-segment")
    public Map<String,Integer> todaySegment() {
        return service.getTodaySegment();
    }
}
