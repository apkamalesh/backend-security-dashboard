package com.example.hcl_project.repository;

import com.example.hcl_project.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findTop5ByOrderByTimeDesc();
}
