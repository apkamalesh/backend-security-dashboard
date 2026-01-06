package com.example.hcl_project.repository;

import com.example.hcl_project.model.LoginActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginActivityRepository extends JpaRepository<LoginActivity, Long> {
    int countByStatus(String status);
}
