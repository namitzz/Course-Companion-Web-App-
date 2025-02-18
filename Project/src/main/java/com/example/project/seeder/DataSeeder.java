package com.example.project.seeder;

import com.example.project.entity.CourseStats;
import com.example.project.repository.CourseStatsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final CourseStatsRepository courseStatsRepository;

    public DataSeeder(CourseStatsRepository courseStatsRepository) {
        this.courseStatsRepository = courseStatsRepository;
    }

    @Override
    public void run(String... args) {
        courseStatsRepository.save(new CourseStats(5, 300)); // 5 courses completed, 300 minutes spent
    }
}

