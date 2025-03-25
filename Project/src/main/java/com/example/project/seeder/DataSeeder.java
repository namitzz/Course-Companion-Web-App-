package com.example.project.seeder;

import com.example.project.model.CourseStats;
import com.example.project.model.Course;
import com.example.project.model.ProgressBar; // Import ProgressBar
import com.example.project.repository.CourseRepository;
import com.example.project.repository.CompletedCourseRepository;
import com.example.project.repository.CourseStatsRepository;
import com.example.project.repository.UserRepository;
import com.example.project.repository.ProgressBarRepository; //  Import ProgressBarRepository

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    // Inject repositories
    private final CourseStatsRepository courseStatsRepository;
    private final CourseRepository courseRepository;
    private final UserRepository UserRepository;
    private final CompletedCourseRepository completedCourseRepository;
    private final ProgressBarRepository progressBarRepository; // Add ProgressBar repository

    // Constructor
    public DataSeeder(CourseStatsRepository courseStatsRepository,
                      CourseRepository courseRepository,
                      UserRepository userRepository,
                      CompletedCourseRepository completedCourseRepository,
                      ProgressBarRepository progressBarRepository) { //  Include ProgressBarRepository
        this.courseStatsRepository = courseStatsRepository;
        this.courseRepository = courseRepository;
        this.UserRepository = userRepository;
        this.completedCourseRepository = completedCourseRepository;
        this.progressBarRepository = progressBarRepository; //  Assign to field
    }

    // Seed data
    @Override
    public void run(String... args) {
        //  Seed CourseStats data
        courseStatsRepository.save(new CourseStats(5, 300));

        // Seed Courses
        Course course1 = new Course("Java Basics", "Learn the basics of Java programming.");
        Course course2 = new Course("Spring Boot", "Build modern web applications with Spring Boot.");
        Course course3 = new Course("Database Design", "Understand relational database design.");
        Course course4 = new Course("REST APIs", "Build and consume RESTful APIs.");

        course1 = courseRepository.save(course1);
        course2 = courseRepository.save(course2);
        course3 = courseRepository.save(course3);
        course4 = courseRepository.save(course4);

        //  Seed ProgressBar Data (Only if table is empty)
        if (progressBarRepository.count() == 0) {
            progressBarRepository.save(new ProgressBar(null, "Java Basics", 10, 7)); // 70% done
            progressBarRepository.save(new ProgressBar(null, "Spring Boot", 15, 5)); // 33% done
            progressBarRepository.save(new ProgressBar(null, "Microservices", 20, 10)); // 50% done
        }

    }
}
