package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GoalScheduler is responsible for periodically checking and updating expired goals.
 * This class runs as a scheduled task in the background.
 */
@Component
public class GoalScheduler {

    private final GoalService goalService;
    private final UserRepository userRepository;

    /**
     * Constructor to inject dependencies.
     *
     * @param goalService    The service that manages goal-related operations.
     * @param userRepository The repository to fetch user data.
     */
    public GoalScheduler(GoalService goalService, UserRepository userRepository) {
        this.goalService = goalService;
        this.userRepository = userRepository;
    }

    /**
     * Scheduled task that runs every 60 seconds to check for expired goals.
     *
     * - Fetches all users from the database.
     * - Iterates through each user and checks if any of their goals have expired.
     * - Calls `goalService.checkExpiredGoals(user)` to handle expiration logic.
     */
    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void checkForExpiredGoals() {
        System.out.println("Running checkForExpiredGoals at " + LocalDateTime.now());

        // Retrieve all users from the database
        List<User> users = userRepository.findAll();

        // Check for expired goals for each user
        for (User user : users) {
            goalService.checkExpiredGoals(user);
        }
    }
}