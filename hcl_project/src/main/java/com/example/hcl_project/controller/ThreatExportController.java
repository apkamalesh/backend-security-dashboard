package com.example.hcl_project.controller;

import com.example.hcl_project.model.ThreatLog;
import com.example.hcl_project.repository.ThreatLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/export")
@CrossOrigin
@RequiredArgsConstructor
public class ThreatExportController {

    private final ThreatLogRepository repo;

    @GetMapping("/threat-logs")
    public void exportCsv(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=threat-logs.csv"
        );

        List<ThreatLog> logs = repo.findTop100ByOrderByTimeDesc();

        PrintWriter writer = response.getWriter();
        writer.println("ID,Category,Threat Type,Severity,Source IP,Device,Time");

        for (ThreatLog t : logs) {
            writer.printf(
                    "%d,%s,%s,%s,%s,%s,%s%n",
                    t.getId(),
                    t.getCategory(),
                    t.getThreatType(),
                    t.getSeverity(),
                    t.getSourceIp(),
                    t.getDevice(),
                    t.getTime()
            );
        }

        writer.flush();
        writer.close();
    }
}
