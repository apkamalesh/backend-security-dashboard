package com.example.hcl_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HclProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(HclProjectApplication.class, args);
	}
}
