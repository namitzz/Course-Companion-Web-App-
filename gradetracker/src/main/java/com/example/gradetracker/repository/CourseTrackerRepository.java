package com.example.gradetracker.repository;

import com.example.gradetracker.model.CourseTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTrackerRepository extends JpaRepository<CourseTracker, Long> {
}
