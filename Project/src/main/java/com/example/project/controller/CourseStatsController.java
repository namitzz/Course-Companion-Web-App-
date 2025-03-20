package com.example.project.controller;

import com.example.project.entity.CourseStats;
import com.example.project.service.CourseStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class CourseStatsController {
    private final CourseStatsService courseStatsService;

    public CourseStatsController(CourseStatsService courseStatsService) {
        this.courseStatsService = courseStatsService;
    }

    @GetMapping
    public ResponseEntity<CourseStats> getCourseStats() {
        return ResponseEntity.ok(courseStatsService.getStats());
    }
}
