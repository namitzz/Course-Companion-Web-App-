package com.example.project.controller;

import com.example.project.service.CourseStatsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    // Constructor
    private final CourseStatsService courseStatsService;
    // Constructor
    public DashboardController(CourseStatsService courseStatsService) {
        this.courseStatsService = courseStatsService;
    }
    // Get dashboard page
    @GetMapping
    public String dashboardPage(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login"; // Redirect if user is not logged in
        }

        // Add attributes to the model
        model.addAttribute("top3Courses", courseStatsService.getTop3PopularCourses());
        // Return the dashboard
        return "dashboard"; // Ensure this maps to an actual view
    }
}
