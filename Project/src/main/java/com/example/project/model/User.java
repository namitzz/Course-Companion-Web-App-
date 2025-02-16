package com.example.project.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CompletedCourse> completedCourses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Badge> badges = new HashSet<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<CompletedCourse> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(Set<CompletedCourse> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public Set<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }

    public void addCompletedCourse(CompletedCourse completedCourse) {
        this.completedCourses.add(completedCourse);
        completedCourse.setUser(this);
    }

    public void removeCompletedCourse(CompletedCourse completedCourse) {
        this.completedCourses.remove(completedCourse);
        completedCourse.setUser(null);
    }

    public void addBadge(Badge badge) {
        this.badges.add(badge);
        badge.setUser(this);
    }

    public void removeBadge(Badge badge) {
        this.badges.remove(badge);
        badge.setUser(null);
    }
}