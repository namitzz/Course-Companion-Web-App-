package com.example.project.service;

import com.example.project.entity.CourseStats;
import com.example.project.repository.CourseStatsRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CourseStatsService {
    private final CourseStatsRepository courseStatsRepository;

    public CourseStatsService(CourseStatsRepository courseStatsRepository) {
        this.courseStatsRepository = courseStatsRepository;
    }

    public CourseStats getStats() {
        return courseStatsRepository.findById(1L).orElse(new CourseStats(0, 0));
    }

    public List<String> getTop3PopularCourses() {
        // Simulate fetching data from a repository
        Map<String, Integer> coursePopularity = fetchCoursePopularityData();

        // Sort courses by popularity and get the top 3
        return coursePopularity.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Map<String, Integer> fetchCoursePopularityData() {
        // Mock implementation, replace with actual data fetching logic
        Map<String, Integer> coursePopularity = new HashMap<>();
        coursePopularity.put("Course A", 120);
        coursePopularity.put("Course B", 150);
        coursePopularity.put("Course C", 90);
        coursePopularity.put("Course D", 200);
        coursePopularity.put("Course E", 180);
        return coursePopularity;
    }
}