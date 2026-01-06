package com.example.hcl_project.service;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.repository.ThreatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ThreatLogRepository repo;

    public Map<String, Integer> getTopStats() {
        return Map.of(
                "totalEvents", (int) repo.count(),
                "critical", repo.countBySeverityIgnoreCase("CRITICAL"),
                "suspicious", repo.countByCategoryIgnoreCase("LOGIN"),
                "apiAttacks", repo.countByCategoryIgnoreCase("API")
        );
    }

    public List<ThreatLog> getRecentAlerts() {
        return repo.findTop10ByOrderByTimeDesc();
    }

    public List<Integer> getRealtimeThreats() {
        LocalDateTime now = LocalDateTime.now();
        List<Integer> list = new ArrayList<>();

        for (int i = 5; i >= 1; i--) {
            list.add(repo.countByTimeBetween(
                    now.minusMinutes(i),
                    now.minusMinutes(i - 1)
            ));
        }
        return list;
    }

    public Map<String, Integer> getDailyActivity() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {
            LocalDate d = today.minusDays(i);
            map.put(d.getDayOfWeek().name(),
                    repo.countByTimeBetween(
                            d.atStartOfDay(),
                            d.atTime(23, 59, 59)
                    ));
        }
        return map;
    }

    public Map<String, Integer> getTodaySegment() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDateTime.now();

        return Map.of(
                "critical", repo.countBySeverityIgnoreCaseAndTimeBetween("CRITICAL", start, end),
                "warnings", repo.countBySeverityIgnoreCaseAndTimeBetween("WARNING", start, end),
                "safe", repo.countBySeverityIgnoreCaseAndTimeBetween("SAFE", start, end)
        );
    }
}
