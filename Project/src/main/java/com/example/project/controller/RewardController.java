package com.example.project.controller;

import com.example.project.model.MysteryBoxReward;
import com.example.project.model.User;
import com.example.project.repository.MysteryBoxRewardRepository;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@CrossOrigin("*")
public class RewardController {

    private final MysteryBoxRewardRepository rewardRepository;
    private final UserService userService;

    @Autowired
    public RewardController(MysteryBoxRewardRepository rewardRepository, UserService userService) {
        this.rewardRepository = rewardRepository;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<List<MysteryBoxReward>> getMyRewards(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = userService.getUserByUsername(userDetails.getUsername());
            return ResponseEntity.ok(rewardRepository.findByUserId(user.getId()));
        }

        return ResponseEntity.status(401).build();
    }
}
