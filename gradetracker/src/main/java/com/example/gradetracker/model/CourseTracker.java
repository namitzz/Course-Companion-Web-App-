package com.example.gradetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class CourseTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Za-z ]+$", message = "Course name must contain only letters")
    @NotBlank(message = "Course name cannot be blank")
    private String name;

    @Min(value = 1, message = "Weight must be at least 1%")
    @Max(value = 100, message = "Weight cannot exceed 100%")
    private int weight;

    public CourseTracker() {}

    public CourseTracker(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
}
