package com.example.project.controller;

import com.example.project.model.Streak;
import com.example.project.model.User;
import com.example.project.service.StreakService;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/streaks")
@CrossOrigin("*")
public class StreakController {

    private final StreakService streakService;
    private final UserService userService;  // Inject UserService

    @Autowired
    public StreakController(StreakService streakService, UserService userService) {
        this.streakService = streakService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<Streak> getStreakForLoggedUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().build();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = userService.getUserByUsername(userDetails.getUsername());  // Fetch user properly
            return ResponseEntity.ok(streakService.getUserStreak(user.getId()));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/me")
    public ResponseEntity<String> updateStreakForLoggedUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().body("User not authenticated.");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = userService.getUserByUsername(userDetails.getUsername());  //  Corrected user fetching
            streakService.updateStreak(user.getId());
            return ResponseEntity.ok("Streak updated!");
        }

        return ResponseEntity.badRequest().body("User not authenticated.");
    }

    @GetMapping("/me/mystery-box")
    public ResponseEntity<String> claimMysteryBoxForLoggedUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().body("User not authenticated.");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = userService.getUserByUsername(userDetails.getUsername());  // Proper user retrieval
            return ResponseEntity.ok(streakService.claimMysteryBox(user.getId()));
        }

        return ResponseEntity.badRequest().body("User not authenticated.");
    }
}
