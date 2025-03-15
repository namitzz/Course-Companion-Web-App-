package com.example.project.service;

import com.example.project.model.Goal;
import com.example.project.model.GoalStatus;
import com.example.project.model.User;
import com.example.project.repository.GoalRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<Goal> getActiveGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.ACTIVE);
    }

    public List<Goal> getExpiredGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.EXPIRED);
    }

    public List<Goal> getCompletedGoals(User user) {
        return goalRepository.findByUserAndStatus(user, GoalStatus.COMPLETED);
    }

    public Goal addGoal(User user, String title, int days, int hours, int minutes) {
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(days).plusHours(hours).plusMinutes(minutes);
        Goal goal = new Goal(user, title, expiresAt);
        return goalRepository.save(goal);
    }

    public Optional<Goal> editGoal(Long goalId, String newTitle, int newDays, int newHours, int newMinutes) {
        Optional<Goal> goalOpt = goalRepository.findById(goalId);
        if (goalOpt.isPresent()) {
            Goal goal = goalOpt.get();
            goal.setTitle(newTitle);
            goal.setExpiresAt(LocalDateTime.now().plusDays(newDays).plusHours(newHours).plusMinutes(newMinutes));
            goalRepository.save(goal);
        }
        return goalOpt;
    }

    public void deleteGoal(Long goalId) {
        goalRepository.deleteById(goalId);
    }

    public void completeGoal(Long goalId) {
        goalRepository.findById(goalId).ifPresent(goal -> {
            goal.setStatus(GoalStatus.COMPLETED);
            goal.setCompletedAt(LocalDateTime.now());
            goalRepository.save(goal);
        });
    }
    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    public void checkExpiredGoals() {
        List<Goal> activeGoals = goalRepository.findByUserAndStatus(null, GoalStatus.ACTIVE);
        for (Goal goal : activeGoals) {
            if (goal.getExpiresAt().isBefore(LocalDateTime.now())) {
                goal.setStatus(GoalStatus.EXPIRED);
                goalRepository.save(goal);
            }
        }
    }


}
