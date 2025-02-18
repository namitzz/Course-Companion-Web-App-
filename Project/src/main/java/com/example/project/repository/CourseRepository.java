package com.example.project.repository;

import com.example.project.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // finding a course by title:
    Course findByTitle(String title);
}