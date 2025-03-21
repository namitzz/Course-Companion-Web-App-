package com.example.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int completedCourses;
    private int totalTimeSpent; // in minutes

    // Constructors
    public CourseStats() {}
    public CourseStats(int completedCourses, int totalTimeSpent) {
        this.completedCourses = completedCourses;
        this.totalTimeSpent = totalTimeSpent;
    }

    // Getters & Setters
    public int getCompletedCourses() { return completedCourses; }
    public void setCompletedCourses(int completedCourses) { this.completedCourses = completedCourses; }
    public int getTotalTimeSpent() { return totalTimeSpent; }
    public void setTotalTimeSpent(int totalTimeSpent) { this.totalTimeSpent = totalTimeSpent; }
}

