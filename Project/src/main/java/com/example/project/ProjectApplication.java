package com.example.project;
// Imported packages
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
// Main class
@SpringBootApplication(scanBasePackages = "com.example.project")
public class ProjectApplication {
    // Main method
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
