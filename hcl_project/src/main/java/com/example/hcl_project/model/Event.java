package com.example.hcl_project.model;

import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String title;
    private String time;

    public Event() {}

    public Event(String type, String title, String time) {
        this.type = type;
        this.title = title;
        this.time = time;
    }

    public Long getId() { return id; }
    public String getType() { return type; }
    public String getTitle() { return title; }
    public String getTime() { return time; }
}
