package com.example.hcl_project.controller;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.service.ThreatLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/threat-logs")
@CrossOrigin
@RequiredArgsConstructor
public class ThreatLogController {

    private final ThreatLogService service;

    @GetMapping
    public List<ThreatLog> all() {
        return service.getAll();
    }

    @GetMapping("/severity/{level}")
    public List<ThreatLog> bySeverity(@PathVariable String level) {
        return service.bySeverity(level);
    }

    @GetMapping("/search")
    public List<ThreatLog> search(@RequestParam String q) {
        return service.search(q);
    }
}
