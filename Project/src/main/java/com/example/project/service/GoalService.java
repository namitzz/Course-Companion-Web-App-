package com.example.project.service;

import com.example.project.model.Goal;
import com.example.project.model.GoalStatus;
import com.example.project.model.User;
import com.example.project.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

 // Service for managing user goals: retrieval, addition, editing, deletion, and completion.

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    // Injects GoalRepository
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    // Fetches active goals for a user
    public List<Goal> getActiveGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.ACTIVE);
    }

    // Fetches Expired goals for a user
    public List<Goal> getExpiredGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.EXPIRED);
    }

    // Fetches completed goals for a user
    public List<Goal> getCompletedGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.COMPLETED);
    }

    // Adds a goal with a specified title and expiration time
    public Goal addGoal(User user, String title, int days, int hours, int minutes) {
        // Calculate the expiration time by adding days, hours, and minutes to the current time
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(days).plusHours(hours).plusMinutes(minutes);
        // Create a new Goal instance and save it
        Goal goal = new Goal(user, title, expiresAt);
        return goalRepository.save(goal);
    }

    // Edits an existing goal's title and expiration time
    public Optional<Goal> editGoal(Long goalId, String newTitle, int newDays, int newHours, int newMinutes) {
        Optional<Goal> goalOpt = goalRepository.findById(goalId);
        if (goalOpt.isPresent()) {
            Goal goal = goalOpt.get();
            // Update goal details
            goal.setTitle(newTitle);
            goal.setExpiresAt(LocalDateTime.now().plusDays(newDays).plusHours(newHours).plusMinutes(newMinutes));
            goalRepository.save(goal);
        }
        return goalOpt;
    }

    // Calculates the remaining time components (days, hours, minutes) until the goal expires
    public int[] calculateTimeComponents(Goal goal) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = goal.getExpiresAt();

        // Calculate total minutes between the current time and goal's expiration time
        long totalMinutes = java.time.Duration.between(now, expiresAt).toMinutes();

        // Calculate days, hours, and minutes from totalMinutes
        int days = (int) (totalMinutes / (24 * 60));
        int hours = (int) ((totalMinutes % (24 * 60)) / 60);
        int minutes = (int) (totalMinutes % 60);

        return new int[]{days, hours, minutes};
    }

    // Deletes a goal based on its ID
    public void deleteGoal(Long goalId) {
        goalRepository.deleteById(goalId);
    }

    // Marks a goal as completed by setting its status to COMPLETED

    public void completeGoal(Long goalId) {
        goalRepository.findById(goalId).ifPresent(goal -> {
            goal.setStatus(GoalStatus.COMPLETED);
            goal.setCompletedAt(LocalDateTime.now());
            goalRepository.save(goal);
        });
    }

    // Retrieves a goal by its ID
    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    // Checks for expired goals for a given user and updates their status if expired
    public void checkExpiredGoals(User user) {
        List<Goal> activeGoals = goalRepository.findByUserAndStatus(user, GoalStatus.ACTIVE);
        for (Goal goal : activeGoals) {
            if (goal.getExpiresAt().isBefore(LocalDateTime.now())) {
                System.out.println("Goal expired: " + goal.getTitle() + " (Expires at: " + goal.getExpiresAt() + ")");
                goal.setStatus(GoalStatus.EXPIRED);
                goalRepository.save(goal);
            }
        }
    }
}