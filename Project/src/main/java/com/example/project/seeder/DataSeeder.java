package com.example.project.seeder;

import com.example.project.entity.CourseStats;
import com.example.project.model.Course;
import com.example.project.model.CompletedCourse;
import com.example.project.model.User;
import com.example.project.repository.CourseRepository;
import com.example.project.repository.CompletedCourseRepository;
import com.example.project.repository.CourseStatsRepository;
import com.example.project.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    // Inject repositories
    private final CourseStatsRepository courseStatsRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CompletedCourseRepository completedCourseRepository;

    // Constructor
    public DataSeeder(CourseStatsRepository courseStatsRepository,
                      CourseRepository courseRepository,
                      UserRepository userRepository,
                      CompletedCourseRepository completedCourseRepository) {
        this.courseStatsRepository = courseStatsRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.completedCourseRepository = completedCourseRepository;
    }

    // Seed data
    @Override
    public void run(String... args) {
        // Clear existing data (optional)
        // completedCourseRepository.deleteAll();
        // courseRepository.deleteAll();
        // userRepository.deleteAll();
        // courseStatsRepository.deleteAll();

        // Seed CourseStats data
        courseStatsRepository.save(new CourseStats(5, 300)); // 5 courses completed, 300 minutes spent

        // Seed Users
        // User user1 = new User("John Doe", "password123");
        // User user2 = new User("Jane Smith", "password456");
        // user1 = userRepository.save(user1); // Save parent record first
        // user2 = userRepository.save(user2);

        // Seed Courses
        Course course1 = new Course("Java Basics", "Learn the basics of Java programming.");
        Course course2 = new Course("Spring Boot", "Build modern web applications with Spring Boot.");
        Course course3 = new Course("Database Design", "Understand relational database design.");
        Course course4 = new Course("REST APIs", "Build and consume RESTful APIs.");

        // Save parent record first
        course1 = courseRepository.save(course1); // Save parent record first
        course2 = courseRepository.save(course2);
        course3 = courseRepository.save(course3);
        course4 = courseRepository.save(course4);

        // Seed Completed Courses (Child records)
        //CompletedCourse completedCourse1 = new CompletedCourse(user1, course1);
        //CompletedCourse completedCourse2 = new CompletedCourse(user1, course2);
        //CompletedCourse completedCourse3 = new CompletedCourse(user2, course2);
        //CompletedCourse completedCourse4 = new CompletedCourse(user2, course3);
        //CompletedCourse completedCourse5 = new CompletedCourse(user2, course2);

        // Save child records
        //completedCourseRepository.save(completedCourse1);
        //completedCourseRepository.save(completedCourse2);
        //completedCourseRepository.save(completedCourse3);
        //completedCourseRepository.save(completedCourse4);
        //completedCourseRepository.save(completedCourse5);

        // Print message
        System.out.println("Dummy data added to the database!");
    }
}