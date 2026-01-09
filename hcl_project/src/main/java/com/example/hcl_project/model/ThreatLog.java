package com.example.hcl_project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "threat_logs")   // ⭐ REQUIRED
@Data
@NoArgsConstructor             // ⭐ REQUIRED for JPA
public class ThreatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String category;     // LOGIN / API / MALWARE

    @Column(nullable = false)
    private String threatType;

    @Column(nullable = false)
    private String severity;     // CRITICAL / WARNING / SAFE

    @Column(nullable = false)
    private String sourceIp;

    @Column(nullable = false)
    private String device;

    @Column(nullable = false)
    private LocalDateTime time;
}
