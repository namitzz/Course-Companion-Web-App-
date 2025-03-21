package com.example.project.controller;

import com.example.project.model.CourseStats;
import com.example.project.service.CourseStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
// Controller for course stats view
@Controller
// Map the controller to the /coursestats path
@RequestMapping("/coursestats")
public class CourseStatsViewController {
    // Constructor
    private final CourseStatsService courseStatsService;
    // Constructor
    public CourseStatsViewController(CourseStatsService courseStatsService) {
        this.courseStatsService = courseStatsService;
    }
    // Get course stats
    @GetMapping
    public String showStats(Model model) {
        // Add attributes to the model
        CourseStats stats = courseStatsService.getStats();
        model.addAttribute("completedCourses", stats.getCompletedCourses());
        model.addAttribute("totalTimeSpent", stats.getTotalTimeSpent());

        // Add attributes to the model
        model.addAttribute("activeCourses", 3);

        // Add attributes to the model
        List<String> top3Courses = courseStatsService.getTop3PopularCourses();
        model.addAttribute("top3Courses", top3Courses);
        // Return the course stats view
        return "coursestats";
    }
}