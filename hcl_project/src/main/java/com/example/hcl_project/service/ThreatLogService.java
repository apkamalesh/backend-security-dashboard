package com.example.hcl_project.service;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.repository.ThreatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThreatLogService {

    private final ThreatLogRepository repo;

    public List<ThreatLog> getAll() {
        return repo.findTop100ByOrderByTimeDesc();
    }

    public List<ThreatLog> bySeverity(String severity) {
        return repo.findBySeverityIgnoreCaseOrderByTimeDesc(severity);
    }

    public List<ThreatLog> search(String q) {
        return repo.search(q);
    }
}
