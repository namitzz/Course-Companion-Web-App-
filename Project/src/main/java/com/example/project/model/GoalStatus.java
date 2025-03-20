package com.example.project.model;

/**
 * Enum representing the different statuses a Goal can have.
 */
public enum GoalStatus {
     // The goal is currently active and in progress.
    ACTIVE,
     // The goal has been successfully completed by the user.
    COMPLETED,
     // The goal's deadline has passed without being completed.
    EXPIRED
}