package com.example.project.model;

import jakarta.persistence.*;

@Entity
public class CompletedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // Helper methods to manage relationships

    /**
     * Assigns this completed course to a user and updates the user's completed courses set.
     * This ensures consistency in the bidirectional relationship.
     */
    public void assignToUser(User user) {
        if (this.user != null) {
            this.user.getCompletedCourses().remove(this); // Remove this completed course from the old user
        }
        this.user = user; // Set the new user
        if (user != null) {
            user.getCompletedCourses().add(this); // Add this completed course to the new user's set
        }
    }

    /**
     * Removes this completed course from its current user.
     * This ensures consistency in the bidirectional relationship.
     */
    public void removeFromUser() {
        if (this.user != null) {
            this.user.getCompletedCourses().remove(this); //
        }
    }
}