package com.example.project.repository;

import com.example.project.model.CourseTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTrackerRepository extends JpaRepository<CourseTracker, Long> {
}
