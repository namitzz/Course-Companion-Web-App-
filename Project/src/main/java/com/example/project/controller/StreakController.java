package com.example.project.controller;

import com.example.project.model.Streak;
import com.example.project.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streaks")
@CrossOrigin("*")
public class StreakController {

    private final StreakService streakService;

    @Autowired
    public StreakController(StreakService streakService) {
        this.streakService = streakService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Streak> getStreak(@PathVariable Long userId) {
        return ResponseEntity.ok(streakService.getUserStreak(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> updateStreak(@PathVariable Long userId) {
        streakService.updateStreak(userId);
        return ResponseEntity.ok("Streak updated!");
    }

    @GetMapping("/{userId}/mystery-box")
    public ResponseEntity<String> claimMysteryBox(@PathVariable Long userId) {
        return ResponseEntity.ok(streakService.claimMysteryBox(userId));
    }
}
