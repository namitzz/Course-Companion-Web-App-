package com.example.project.service;

import com.example.project.model.*;
import com.example.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CompletedCourseRepository completedCourseRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    public void completeCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Check if the user has already completed this course
        if (completedCourseRepository.existsByUserAndCourseId(user, courseId)) {
            throw new RuntimeException("Course already completed by the user");
        }

        // Mark the course as completed
        CompletedCourse completedCourse = new CompletedCourse();
        completedCourse.setUser(user);
        completedCourse.setCourse(course);
        completedCourseRepository.save(completedCourse);

        // Check and award badges
        checkAndAwardBadges(user);
    }

    private void checkAndAwardBadges(User user) {
        List<CompletedCourse> completedCourses = completedCourseRepository.findByUser(user);
        int completedCount = completedCourses.size();

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

    private boolean userHasBadge(User user, String badgeName) {
        return user.getBadges().stream().anyMatch(badge -> badge.getName().equals(badgeName));
    }

    private void awardBadge(User user, String badgeName) {
        Badge badge = new Badge();
        badge.setName(badgeName);
        badge.setUser(user);
        badgeRepository.save(badge);

        // Refresh user data to update the badge list
        user = userRepository.findById(user.getId()).orElse(null);
    }

}