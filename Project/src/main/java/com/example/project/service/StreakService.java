package com.example.project.service;

import com.example.project.model.MysteryBoxReward;
import com.example.project.model.Streak;
import com.example.project.model.User;
import com.example.project.repository.MysteryBoxRewardRepository;
import com.example.project.repository.StreakRepository;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StreakService {

    private final StreakRepository streakRepository;
    private final UserRepository userRepository;
    private final MysteryBoxRewardRepository rewardRepository;

    private static final List<String> MYSTERY_REWARDS = List.of(
            "Bonus XP", "Being cool badge", "5 Pound discount", "Extra Points" ,"Daily Boost",
            " Profile Glow",
            "Double XP",
            "Secret Achievement"
    );

    public Streak getUserStreak(Long userId) {
        return streakRepository.findByUserId(userId).orElseGet(() -> {
            Streak newStreak = new Streak();
            newStreak.setUserId(userId);
            return streakRepository.save(newStreak);
        });
    }

    public Streak updateStreak(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Streak streak = streakRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Streak newStreak = new Streak();
                    newStreak.setUserId(userId);
                    return newStreak;
                });

        LocalDate today = LocalDate.now();
        LocalDate lastDate = streak.getLastActivityDate();

        if (lastDate == null || !lastDate.equals(today.minusDays(1))) {
            streak.setStreakCount(1);
        } else {
            streak.setStreakCount(streak.getStreakCount() + 1);
        }

        streak.setLastActivityDate(today);

        // Unlock box every 5-day streak
        streak.setMysteryBoxAvailable(streak.getStreakCount() % 5 == 0);

        return streakRepository.save(streak);
    }

    public String claimMysteryBox(Long userId) {
        Streak streak = streakRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No streak found"));

        if (!streak.isMysteryBoxAvailable()) {
            return "No mystery box available. Keep your streak going!";
        }

        String reward = MYSTERY_REWARDS.get(new Random().nextInt(MYSTERY_REWARDS.size()));

        // Save reward
        MysteryBoxReward rewardEntity = new MysteryBoxReward();
        rewardEntity.setUserId(userId);
        rewardEntity.setReward(reward);
        rewardRepository.save(rewardEntity);

        // Update streak box status
        streak.setMysteryBoxAvailable(false);
        streakRepository.save(streak);

        // Award XP based on reward type
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int xpToAdd = switch (reward) {
            case "Bonus XP" -> 30;
            case "Double XP" -> 50;
            case "Daily Boost" -> 20;
            case "Extra Points" -> 15;
            case "Secret Achievement" -> 100;
            default -> 0;
        };

        if (xpToAdd > 0) {
            user.addXp(xpToAdd);  // uses setXp() → triggers level update
            userRepository.save(user);
        }

        return reward;
    }

}
