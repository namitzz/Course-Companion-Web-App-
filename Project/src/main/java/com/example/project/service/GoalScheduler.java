package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

// Periodically checks and updates expired goals
@Component
public class GoalScheduler {

    private final GoalService goalService;
    private final UserRepository userRepository;

     // Injects dependencies
    public GoalScheduler(GoalService goalService, UserRepository userRepository) {
        this.goalService = goalService;
        this.userRepository = userRepository;
    }

    /**
     * Runs every 60 seconds to check for expired goals
     * Iterates through all users and updates expired goals
     */
    @Scheduled(fixedRate = 60000)
    public void checkForExpiredGoals() {
        System.out.println("Running checkForExpiredGoals at " + LocalDateTime.now());

        // Retrieve users and check for expired goals
        for (User user : userRepository.findAll()) {
            goalService.checkExpiredGoals(user);
        }
    }
}