package com.example.project.controller;

import com.example.project.entity.CourseStats;
import com.example.project.service.CourseStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class CourseStatsController {
    private final CourseStatsService courseStatsService;
    // Constructor
    public CourseStatsController(CourseStatsService courseStatsService) {
        this.courseStatsService = courseStatsService;
    }
    // Get course stats
    @GetMapping
    public ResponseEntity<CourseStats> getCourseStats() {
        return ResponseEntity.ok(courseStatsService.getStats());
    }
    // Get top 3 courses
    @GetMapping("/top3courses")
    public ResponseEntity<List<String>> getTop3Courses() {
        return ResponseEntity.ok(courseStatsService.getTop3PopularCourses());
    }
}