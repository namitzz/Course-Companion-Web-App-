package com.example.project.repository;

import com.example.project.model.CompletedCourse;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedCourseRepository extends JpaRepository<CompletedCourse, Long> {
    // finds all completed courses by a specific user
    List<CompletedCourse> findByUser(User user);

    // checks if a user has already completed a specific course
    boolean existsByUserAndCourseId(User user, Long courseId);
}