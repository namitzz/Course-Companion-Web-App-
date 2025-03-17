package com.example.project.service;

import com.example.project.model.Goal;
import com.example.project.model.GoalStatus;
import com.example.project.model.User;
import com.example.project.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing goal-related operations:
 * - Retrieving, adding, editing, deleting, and completing goals
 * - Checking for expired goals
 */
@Service
public class GoalService {

    private final GoalRepository goalRepository;

    /**
     * Constructor to inject the GoalRepository.
     *
     * @param goalRepository The repository used to interact with the database for goal-related operations.
     */
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    /**
     * Retrieves all active goals for the specified user.
     *
     * @param user The user whose active goals are to be fetched.
     * @return A list of active goals for the user.
     */
    public List<Goal> getActiveGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.ACTIVE);
    }

    /**
     * Retrieves all expired goals for the specified user.
     *
     * @param user The user whose expired goals are to be fetched.
     * @return A list of expired goals for the user.
     */
    public List<Goal> getExpiredGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.EXPIRED);
    }

    /**
     * Retrieves all completed goals for the specified user.
     *
     * @param user The user whose completed goals are to be fetched.
     * @return A list of completed goals for the user.
     */
    public List<Goal> getCompletedGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.COMPLETED);
    }

    /**
     * Adds a new goal for a user with the specified title and time duration.
     *
     * @param user   The user who is adding the goal.
     * @param title  The title of the new goal.
     * @param days   The number of days after which the goal will expire.
     * @param hours  The number of hours after which the goal will expire.
     * @param minutes The number of minutes after which the goal will expire.
     * @return The saved goal object.
     */
    public Goal addGoal(User user, String title, int days, int hours, int minutes) {
        // Calculate the expiration time by adding days, hours, and minutes to the current time
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(days).plusHours(hours).plusMinutes(minutes);
        // Create a new Goal instance and save it
        Goal goal = new Goal(user, title, expiresAt);
        return goalRepository.save(goal);
    }

    /**
     * Edits an existing goal's title and expiration time.
     *
     * @param goalId      The ID of the goal to be edited.
     * @param newTitle    The new title for the goal.
     * @param newDays     The new number of days for expiration.
     * @param newHours    The new number of hours for expiration.
     * @param newMinutes  The new number of minutes for expiration.
     * @return The edited goal if it exists, or an empty Optional if not found.
     */
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

    /**
     * Calculates the remaining time components (days, hours, minutes) until the goal expires.
     *
     * @param goal The goal whose time components are to be calculated.
     * @return An array containing days, hours, and minutes remaining until the goal's expiration.
     */
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

    /**
     * Deletes a goal based on its ID.
     *
     * @param goalId The ID of the goal to be deleted.
     */
    public void deleteGoal(Long goalId) {
        goalRepository.deleteById(goalId);
    }

    /**
     * Marks a goal as completed by setting its status to COMPLETED.
     *
     * @param goalId The ID of the goal to be marked as completed.
     */
    public void completeGoal(Long goalId) {
        goalRepository.findById(goalId).ifPresent(goal -> {
            goal.setStatus(GoalStatus.COMPLETED);
            goal.setCompletedAt(LocalDateTime.now());
            goalRepository.save(goal);
        });
    }

    /**
     * Retrieves a goal by its ID.
     *
     * @param id The ID of the goal to be retrieved.
     * @return The goal if found, or an empty Optional if not found.
     */
    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    /**
     * Checks for expired goals for a given user and updates their status if expired.
     *
     * @param user The user whose active goals will be checked for expiration.
     */
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