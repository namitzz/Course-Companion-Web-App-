package com.example.project.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity // Marks this class as a JPA entity
@Table(name = "users") // Specifies the table name in the database
public class User implements Serializable {
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    private Long id;

    @Column(unique = true, nullable = false) // Ensures username is unique and not null
    private String username;

    @Column(nullable = false) // Ensures password is not null
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Roles are eagerly fetched
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id")) // Roles stored in a separate table
    @Column(name = "role") // Column name for roles
    private Set<String> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // One user can have many badges
    private Set<Badge> badges = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // One user can have many completed courses
    private Set<CompletedCourse> completedCourses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true) // Goals are removed when user is deleted
    private Set<Goal> goals = new HashSet<>();

    // Default constructor
    public User() {}

    // Constructor with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>(); // Initialize empty roles set
    }

    // Constructor with username, password, and roles
    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles != null ? new HashSet<>(roles) : new HashSet<>(); // Initialize roles if provided
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoles() { return roles; }

    public void setRoles(Set<String> roles) {
        this.roles = roles != null ? new HashSet<>(roles) : new HashSet<>(); // Ensure non-null roles set
    }

    public Set<Badge> getBadges() { return badges; }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }

    public Set<CompletedCourse> getCompletedCourses() { return completedCourses; }

    public void setCompletedCourses(Set<CompletedCourse> completedCourses) {
        this.completedCourses = completedCourses;
    }
}