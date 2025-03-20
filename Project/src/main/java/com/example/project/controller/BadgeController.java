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

@Controller // Spring MVC Controller
public class BadgeController {

    @Autowired // Inject BadgeService
    private BadgeService badgeService;

    @Autowired // Inject UserService
    private UserService userService;

    /**
     * GET /badges - Display user badges and completed courses.
     */
    @GetMapping("/badges")
    public String viewBadges(@RequestParam(required = false) Long userId, Model model) {
        if (userId == null) {
            model.addAttribute("error", "User ID required.");
            return "badges";
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            model.addAttribute("error", "User not found.");
            return "badges";
        }

        // Add user data to model
        model.addAttribute("user", user);
        model.addAttribute("badges", user.getBadges());
        model.addAttribute("completedCourses", user.getCompletedCourses());

        return "badges";
    }

    /**
     * POST /complete-course - Mark course as completed and award badges.
     */
    @PostMapping("/complete-course")
    public String completeCourse(@RequestParam Long userId, @RequestParam(required = false) String courseId, Model model) {
        try {
            if (courseId == null || courseId.trim().isEmpty()) {
                model.addAttribute("error", "Course ID required.");
                return viewBadges(userId, model);
            }

            // Convert courseId to Long
            Long courseIdLong;
            try {
                courseIdLong = Long.parseLong(courseId.trim());
            } catch (NumberFormatException e) {
                model.addAttribute("error", "Invalid Course ID.");
                return viewBadges(userId, model);
            }

            // Complete course and check for badges
            badgeService.completeCourse(userId, courseIdLong);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return viewBadges(userId, model);
        }

        // Redirect to badges page
        return "redirect:/badges?userId=" + userId;
    }
}