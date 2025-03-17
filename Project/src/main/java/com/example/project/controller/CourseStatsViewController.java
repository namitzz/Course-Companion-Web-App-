package com.example.project.controller;

import com.example.project.entity.CourseStats;
import com.example.project.service.CourseStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coursestats")
public class CourseStatsViewController {

    private final CourseStatsService courseStatsService;

    public CourseStatsViewController(CourseStatsService courseStatsService) {
        this.courseStatsService = courseStatsService;
    }

    @GetMapping
    public String showStats(Model model) {

        CourseStats stats = courseStatsService.getStats();
        model.addAttribute("completedCourses", stats.getCompletedCourses());
        model.addAttribute("totalTimeSpent", stats.getTotalTimeSpent());


        model.addAttribute("activeCourses", 3);


        List<String> top3Courses = courseStatsService.getTop3PopularCourses();
        model.addAttribute("top3Courses", top3Courses);

        return "coursestats";
    }
}
