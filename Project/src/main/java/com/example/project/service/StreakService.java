package com.example.project.service;

import com.example.project.model.Streak;
import com.example.project.model.User;
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

    private static final List<String> MYSTERY_REWARDS = List.of("Bonus XP", "Exclusive Badge", "Discount Code", "Extra Points");

    public Streak getUserStreak(Long userId) {
        return streakRepository.findByUserId(userId).orElseGet(() -> {
            Streak newStreak = new Streak();
            newStreak.setUserId(userId);
            return newStreak;
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
        if (streak.getLastActivityDate() == null || !streak.getLastActivityDate().equals(today.minusDays(1))) {
            streak.setStreakCount(1); // Reset streak if not consecutive
        } else {
            streak.setStreakCount(streak.getStreakCount() + 1); // Increment streak
        }

        streak.setLastActivityDate(today);

        // Unlock a mystery box at streak milestones
        streak.setMysteryBoxAvailable(streak.getStreakCount() % 5 == 0);

        return streakRepository.save(streak);
    }

    public String claimMysteryBox(Long userId) {
        Streak streak = streakRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No streak found"));

        if (!streak.isMysteryBoxAvailable()) {
            return "No mystery box available. Keep your streak going!";
        }

        // Select a random reward
        String reward = MYSTERY_REWARDS.get(new Random().nextInt(MYSTERY_REWARDS.size()));
        streak.setMysteryBoxAvailable(false); // Mark the box as claimed

        streakRepository.save(streak);
        return "Congratulations! You won a: " + reward;
    }
}
