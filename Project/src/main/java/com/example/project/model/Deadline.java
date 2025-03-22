package com.example.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Deadline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    private String taskType;
    private int taskWeight; // Percentage (1-100)
    private LocalDate dueDate;

    public Deadline() {}

    public Deadline(String courseName, String taskType, int taskWeight, LocalDate dueDate) {
        this.courseName = courseName;
        this.taskType = taskType;
        this.taskWeight = taskWeight;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getTaskWeight() {
        return taskWeight;
    }

    public void setTaskWeight(int taskWeight) {
        this.taskWeight = taskWeight;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Deadline(Long id, String courseName, String taskType, int taskWeight, LocalDate dueDate) {
        this.id = id;
        this.courseName = courseName;
        this.taskType = taskType;
        this.taskWeight = taskWeight;
        this.dueDate = dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
