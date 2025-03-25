package com.example.project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressBar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private int totalLessons;
    private int completedLessons;

    public int getProgressPercentage() {
        return (int) ((double) completedLessons / totalLessons * 100);
    }

    public void setProgressPercentage(int progressPercentage) {
    }
}
