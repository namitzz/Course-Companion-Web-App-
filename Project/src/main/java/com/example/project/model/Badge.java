package com.example.project.model;

import jakarta.persistence.*;

@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
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
     * Sets the user for this badge and updates the user's badge set.
     * This ensures consistency in the bidirectional relationship.
     */
    public void assignToUser(User user) {
        if (this.user != null) {
            this.user.getBadges().remove(this); // Remove this badge from the old user
        }
        this.user = user; // Set the new user
        if (user != null) {
            user.getBadges().add(this); // Add this badge to the new user's badge set
        }
    }

    /**
     * Removes this badge from its current user.
     * This ensures consistency in the bidirectional relationship.
     */
    public void removeFromUser() {
        if (this.user != null) {
            this.user.getBadges().remove(this); // Remove this badge from the user
            this.user = null; // Clear the user reference
        }
    }
}