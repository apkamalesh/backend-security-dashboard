package com.example.hcl_project.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class ThreatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;      // LOGIN / API / MALWARE
    private String threatType;
    private String severity;      // CRITICAL / WARNING / SAFE
    private String sourceIp;
    private String device;
    private LocalDateTime time;
}
