package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.CourseStatsService;
import com.example.project.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final UserService userService;
    private final CourseStatsService courseStatsService;

    public DashboardController(UserService userService, CourseStatsService courseStatsService) {
        this.userService = userService;
        this.courseStatsService = courseStatsService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login"; // Redirect if no user is logged in
        }

        User user = userService.getUserByUsername(userDetails.getUsername());

        // Add user info to the model
        model.addAttribute("user", user);
        model.addAttribute("xp", user.getXp());
        model.addAttribute("level", user.getLevel());
        model.addAttribute("userBadges", user.getBadges());


        // Add top 3 popular courses
        List<String> top3Courses = courseStatsService.getTop3PopularCourses();
        model.addAttribute("top3Courses", top3Courses);

        return "dashboard"; // Thymeleaf view
    }

}
