package com.example.hcl_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.hcl_project.repository")
@EntityScan(basePackages = "com.example.hcl_project.model")
public class HclProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(HclProjectApplication.class, args);
	}
}

