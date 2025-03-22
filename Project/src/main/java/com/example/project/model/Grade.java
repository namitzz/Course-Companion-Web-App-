package com.example.project.model;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    @ElementCollection
    @CollectionTable(name = "component_weights", joinColumns = @JoinColumn(name = "grade_id"))
    @MapKeyColumn(name = "component_name")
    @Column(name = "weight")
    private Map<String, Double> componentWeights = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "component_grades", joinColumns = @JoinColumn(name = "grade_id"))
    @MapKeyColumn(name = "component_name")
    @Column(name = "grade")
    private Map<String, Double> componentGrades = new HashMap<>();

    public Grade() {}

    public Grade(String courseName) {
        this.courseName = courseName;
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

    public Map<String, Double> getComponentWeights() {
        return componentWeights;
    }

    public void setComponentWeights(Map<String, Double> componentWeights) {
        this.componentWeights = componentWeights;
    }

    public Map<String, Double> getComponentGrades() {
        return componentGrades;
    }

    public void setComponentGrades(Map<String, Double> componentGrades) {
        this.componentGrades = componentGrades;
    }

    public double calculateOverallGradeSoFar() {
        double totalWeight = componentWeights.values().stream().mapToDouble(Double::doubleValue).sum();
        if (totalWeight != 100) {
            throw new IllegalStateException("Total weight must be 100%. Current total weight: " + totalWeight + "%");
        }

        double overallGrade = 0.0;
        double completedWeight = 0.0;

        for (Map.Entry<String, Double> entry : componentWeights.entrySet()) {
            String component = entry.getKey();
            double weight = entry.getValue();
            Double grade = componentGrades.get(component);

            if (grade != null) {
                overallGrade += (grade * weight) / 100;
                completedWeight += weight;
            }
        }

        overallGrade = Math.round(overallGrade * 100) / 100.0;

        return overallGrade;
    }

    public double calculateRemainingWeight() {
        double completedWeight = componentGrades.keySet().stream()
                .mapToDouble(component -> componentWeights.get(component))
                .sum();
        return 100 - completedWeight;
    }
}