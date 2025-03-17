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

    private final CourseStatsService courseStatsService;

    public DashboardController(CourseStatsService courseStatsService) {
        this.courseStatsService = courseStatsService;
    }

    @GetMapping
    public String dashboardPage(Authentication authentication, Model model) {
        if (authentication == null) {
            return "redirect:/login"; // Redirect if user is not logged in
        }


        model.addAttribute("top3Courses", courseStatsService.getTop3PopularCourses());

        return "dashboard"; // Ensure this maps to an actual view
    }
}
