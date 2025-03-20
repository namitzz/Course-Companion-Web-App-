package com.example.project.service;

import com.example.project.model.*;
import com.example.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service
public class BadgeService {

    @Autowired // Injects UserRepository
    private UserRepository userRepository;

    @Autowired // Injects CourseRepository
    private CourseRepository courseRepository;

    @Autowired // Injects CompletedCourseRepository
    private CompletedCourseRepository completedCourseRepository;

    @Autowired // Injects BadgeRepository
    private BadgeRepository badgeRepository;

    /**
     * Marks a course as completed for a user and awards badges if applicable.
     */
    public void completeCourse(Long userId, Long courseId) {
        // Fetch user and course, throw exceptions if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Check if the user has already completed the course
        if (completedCourseRepository.existsByUserAndCourseId(user, courseId)) {
            throw new RuntimeException("Course already completed by the user");
        }

        // Mark the course as completed
        CompletedCourse completedCourse = new CompletedCourse();
        completedCourse.setUser(user);
        completedCourse.setCourse(course);
        completedCourseRepository.save(completedCourse);

        // Check and award badges based on completed courses
        checkAndAwardBadges(user);
    }

    /**
     * Checks the number of completed courses and awards badges if conditions are met.
     */
    private void checkAndAwardBadges(User user) {
        List<CompletedCourse> completedCourses = completedCourseRepository.findByUser(user);
        int completedCount = completedCourses.size();

        // Award badges based on completion milestones
        if (completedCount >= 5 && !userHasBadge(user, "Bronze Learner")) {
            awardBadge(user, "Bronze Learner");
        }
        if (completedCount >= 10 && !userHasBadge(user, "Silver Learner")) {
            awardBadge(user, "Silver Learner");
        }
        if (completedCount >= 20 && !userHasBadge(user, "Gold Learner")) {
            awardBadge(user, "Gold Learner");
        }
    }

    /**
     * Checks if the user already has a specific badge.
     */
    private boolean userHasBadge(User user, String badgeName) {
        return user.getBadges().stream().anyMatch(badge -> badge.getName().equals(badgeName));
    }

    /**
     * Awards a badge to the user and updates the database.
     */
    private void awardBadge(User user, String badgeName) {
        Badge badge = new Badge();
        badge.setName(badgeName); // Set badge name
        badge.setUser(user); // Assign badge to user
        badgeRepository.save(badge); // Save badge to database

        // Refresh user data to include the new badge
        user = userRepository.findById(user.getId()).orElse(null);
    }
}