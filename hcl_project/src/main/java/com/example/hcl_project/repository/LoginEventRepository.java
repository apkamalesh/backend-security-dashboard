package com.example.hcl_project.repository;

import com.example.hcl_project.model.LoginEvent;
import com.example.hcl_project.model.LoginStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoginEventRepository extends JpaRepository<LoginEvent, Long> {

    long countByStatus(LoginStatus status);

    List<LoginEvent> findTop5ByOrderByTimeDesc();
}
