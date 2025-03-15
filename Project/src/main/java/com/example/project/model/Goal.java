package com.example.project.model;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime expiresAt;

    @Column(nullable = true)
    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoalStatus status;

    public Goal() {
        this.createdAt = LocalDateTime.now();
        this.status = GoalStatus.ACTIVE;
    }

    public Goal(User user, String title, LocalDateTime expiresAt) {
        this.user = user;
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = expiresAt;
        this.status = GoalStatus.ACTIVE;
    }

    // Getters and Setters
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

    // Calculate the number of days between createdAt and expiresAt (or current date if expiresAt is null)
    public long getDays() {
        LocalDateTime end = (expiresAt != null) ? expiresAt : LocalDateTime.now();
        Duration duration = Duration.between(createdAt, end);
        return duration.toDays();
    }

    // Calculate the number of hours between createdAt and expiresAt (or current date if expiresAt is null)
    public long getHours() {
        if (expiresAt != null) {
            return java.time.Duration.between(createdAt, expiresAt).toHours();
        }
        return 0; // Or some other logic if expiresAt is null
    }

    // Calculate the number of minutes between createdAt and expiresAt (or current date if expiresAt is null)
    public long getMinutes() {
        LocalDateTime end = (expiresAt != null) ? expiresAt : LocalDateTime.now();
        Duration duration = Duration.between(createdAt, end);
        return duration.toMinutes();
    }
}
