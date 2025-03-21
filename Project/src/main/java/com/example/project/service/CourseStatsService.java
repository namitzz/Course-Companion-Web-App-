package com.example.project.service;

import com.example.project.model.CourseStats;
import com.example.project.repository.CompletedCourseRepository;
import com.example.project.repository.CourseStatsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseStatsService {
    // Service class for CourseStats
    private final CourseStatsRepository courseStatsRepository;
    private final CompletedCourseRepository completedCourseRepository;
    // Constructor
    public CourseStatsService(CourseStatsRepository courseStatsRepository,
                              CompletedCourseRepository completedCourseRepository) {
        this.courseStatsRepository = courseStatsRepository;
        this.completedCourseRepository = completedCourseRepository;
    }
    // Method to get the stats
    public CourseStats getStats() {
        return courseStatsRepository.findById(1L).orElse(new CourseStats(0, 0));
    }
    // Method to update the stats
    public List<String> getTop3PopularCourses() {
        Pageable topThree = PageRequest.of(0, 3);
        return completedCourseRepository.findTop3PopularCourses(topThree);
    }
}