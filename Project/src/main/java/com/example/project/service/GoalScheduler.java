package com.example.project.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GoalScheduler {
    private final GoalService goalService;

    public GoalScheduler(GoalService goalService) {
        this.goalService = goalService;
    }

    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void checkForExpiredGoals() {
        goalService.checkExpiredGoals();
    }
}
