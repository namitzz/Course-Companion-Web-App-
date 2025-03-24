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

    // POST to manually add XP
    @PostMapping("/add")
    public String addXp(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int amount) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        user.setXp(user.getXp() + amount); // level updates automatically
        userService.save(user);
        return "XP updated to " + user.getXp() + ", Level: " + user.getLevel();
    }

    // POST to reset XP
    @PostMapping("/reset")
    public String resetXp(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        user.setXp(0); // resets level via setXp logic
        userService.save(user);
        return "XP reset successfully.";
    }
}
