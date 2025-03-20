package com.example.project.service;

import com.example.project.entity.CourseStats;
import com.example.project.repository.CourseStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseStatsService {
    private final CourseStatsRepository courseStatsRepository;

    public CourseStatsService(CourseStatsRepository courseStatsRepository) {
        this.courseStatsRepository = courseStatsRepository;
    }

    public CourseStats getStats() {
        return courseStatsRepository.findById(1L).orElse(new CourseStats(0, 0));
    }
}
