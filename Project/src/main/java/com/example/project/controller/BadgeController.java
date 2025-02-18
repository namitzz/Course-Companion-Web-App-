package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.BadgeService;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private UserService userService;

    // Display the user's badges and course completion status
    @GetMapping("/badges")
    public String viewBadges(@RequestParam Long userId, Model model) {
        try {
            // Fetch the user and their badges
            User user = userService.getUserById(userId);
            model.addAttribute("user", user);
            model.addAttribute("badges", user.getBadges());
            model.addAttribute("completedCourses", user.getCompletedCourses());
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "badges";
    }

    // Handle course completion and badge awarding
    @PostMapping("/complete-course")
    public String completeCourse(@RequestParam Long userId, @RequestParam String courseId, Model model) {
        try {
            // Validate courseId
            if (courseId == null || courseId.trim().isEmpty()) {
                throw new RuntimeException("Course ID cannot be empty.");
            }

            // Convert courseId to Long
            Long courseIdLong;
            try {
                courseIdLong = Long.parseLong(courseId.trim());
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid Course ID. Please enter a valid number.");
            }

            // Mark the course as completed and check for badges
            badgeService.completeCourse(userId, courseIdLong);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return viewBadges(userId, model); // Return to the badges page with an error message
        }

        // Redirect to the badges page to show updated information
        return "redirect:/badges?userId=" + userId;
    }
}