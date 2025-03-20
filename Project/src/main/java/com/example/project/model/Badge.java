package com.example.project.model;

import jakarta.persistence.*;

@Entity // Marks this class as a JPA entity
public class Badge {
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    private Long id;

    private String name; // Name of the badge

    @ManyToOne // Many badges can belong to one user
    @JoinColumn(name = "user_id") // Foreign key column in the database
    private User user;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Helper methods to manage the relationship with User

    /**
     * Assigns this badge to a user and updates the user's badge set.
     */
    public void assignToUser(User user) {
        if (this.user != null) {
            this.user.getBadges().remove(this); // Remove from old user
        }
        this.user = user; // Set new user
        if (user != null) {
            user.getBadges().add(this); // Add to new user's badge set
        }
    }

    /**
     * Removes this badge from its current user.
     */
    public void removeFromUser() {
        if (this.user != null) {
            this.user.getBadges().remove(this); // Remove from user
            this.user = null; // Clear user reference
        }
    }
}