package com.example.hcl_project.repository;

import com.example.hcl_project.model.ThreatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ThreatLogRepository extends JpaRepository<ThreatLog, Long> {

    List<ThreatLog> findTop10ByOrderByTimeDesc();
    List<ThreatLog> findTop100ByOrderByTimeDesc();

    int countByTimeBetween(LocalDateTime start, LocalDateTime end);

    int countBySeverityIgnoreCase(String severity);
    int countByCategoryIgnoreCase(String category);

    int countBySeverityIgnoreCaseAndTimeBetween(
            String severity,
            LocalDateTime start,
            LocalDateTime end
    );

    List<ThreatLog> findBySeverityIgnoreCaseOrderByTimeDesc(String severity);

    @Query("""
        SELECT t FROM ThreatLog t
        WHERE LOWER(t.threatType) LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(t.sourceIp) LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(t.device) LIKE LOWER(CONCAT('%', :q, '%'))
    """)
    List<ThreatLog> search(@Param("q") String q);
}
