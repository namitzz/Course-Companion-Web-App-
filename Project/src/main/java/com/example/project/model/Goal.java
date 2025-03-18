package com.example.project.model;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Represents a Goal entity mapped to the "goals" table in the database.
 */
@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
    private Long id;

    @ManyToOne // Many goals can belong to one user
    @JoinColumn(name = "user_id", nullable = false) // Foreign key reference to User entity
    private User user;

    @Column(nullable = false) // Goal title is required
    private String title;

    @Column(nullable = false) // Timestamp when the goal was created
    private LocalDateTime createdAt;

    @Column(nullable = true) // Expiration timestamp (nullable since some goals may not expire)
    private LocalDateTime expiresAt;

    @Column(nullable = true) // Completion timestamp (nullable since not all goals are completed)
    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING) // Stores ENUM as a string in the database
    @Column(nullable = false) // Status is required
    private GoalStatus status;

    /**
     * Default constructor that initializes the goal with the current timestamp and sets the status to ACTIVE.
     */
    public Goal() {
        this.createdAt = LocalDateTime.now();
        this.status = GoalStatus.ACTIVE;
    }

    /**
     * Constructor to initialize a goal with a specific user, title, and expiration date.
     *
     * @param user The user associated with the goal.
     * @param title The title of the goal.
     * @param expiresAt The expiration date/time of the goal.
     */
    public Goal(User user, String title, LocalDateTime expiresAt) {
        this.user = user;
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
        this.status = GoalStatus.ACTIVE;
    }

    // ======== GETTERS AND SETTERS ========

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public GoalStatus getStatus() { return status; }
    public void setStatus(GoalStatus status) { this.status = status; }

    // ======== HELPER METHODS ========

    /**
     * Calculates the number of days between the creation date and expiration date.
     * If the goal has no expiration date, it uses the current date.
     *
     * @return The number of days.
     */
    public long getDays() {
        LocalDateTime end = (expiresAt != null) ? expiresAt : LocalDateTime.now();
        Duration duration = Duration.between(createdAt, end);
        return duration.toDays();
    }

    /**
     * Calculates the number of hours between the creation date and expiration date.
     * Returns 0 if no expiration date is set.
     *
     * @return The number of hours.
     */
    public long getHours() {
        if (expiresAt != null) {
            return Duration.between(createdAt, expiresAt).toHours();
        }
        return 0; // Default value if expiresAt is null
    }

    /**
     * Calculates the number of minutes between the creation date and expiration date.
     * If no expiration date is set, it uses the current date.
     *
     * @return The number of minutes.
     */
    public long getMinutes() {
        LocalDateTime end = (expiresAt != null) ? expiresAt : LocalDateTime.now();
        Duration duration = Duration.between(createdAt, end);
        return duration.toMinutes();
    }
}