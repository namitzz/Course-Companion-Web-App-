package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xp")
public class XPController {

    private final UserService userService;

    @Autowired
    public XPController(UserService userService) {
        this.userService = userService;
    }

    // GET current user's XP
    @GetMapping("/me")
    public int getXp(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return user.getXp();
    }

    // GET current user's Level
    @GetMapping("/level")
    public int getLevel(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return user.getLevel();
    }

    // POST to manually add XP (e.g. admin or for testing)
    @PostMapping("/add")
    public String addXp(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int amount) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        int newXp = user.getXp() + amount;
        int newLevel = (newXp / 100) + 1;
        user.setXp(newXp);
        user.setLevel(newLevel);
        userService.save(user);
        return "XP updated to " + newXp + ", Level: " + newLevel;
    }

    // Optional: POST to reset XP (for debug or gamification resets)
    @PostMapping("/reset")
    public String resetXp(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        user.setXp(0);
        user.setLevel(1);
        userService.save(user);
        return "XP reset successfully.";
    }
}
