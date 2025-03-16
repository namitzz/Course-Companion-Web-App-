package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class GoalScheduler {
    private final GoalService goalService;
    private final UserRepository userRepository;

    public GoalScheduler(GoalService goalService, UserRepository userRepository) {
        this.goalService = goalService;
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void checkForExpiredGoals() {
        System.out.println("Running checkForExpiredGoals at " + LocalDateTime.now());
        List<User> users = userRepository.findAll(); // Get all users
        for (User user : users) {
            goalService.checkExpiredGoals(user);
        }
    }
}
