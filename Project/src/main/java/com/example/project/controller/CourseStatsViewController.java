package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Controller
@RequestMapping("/coursestats")
public class CourseStatsViewController {

    @GetMapping
    public String showStats(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/api/stats";
        Map stats = restTemplate.getForObject(apiUrl, Map.class);

        model.addAttribute("completedCourses", stats.get("completedCourses"));
        model.addAttribute("totalTimeSpent", stats.get("totalTimeSpent"));

        // Add a new field for active courses (mocked for now)
        model.addAttribute("activeCourses", 3); // Example: Hardcoded active courses

        return "coursestats";
    }
}
