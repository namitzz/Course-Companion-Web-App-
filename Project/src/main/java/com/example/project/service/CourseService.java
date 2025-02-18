package com.example.project.service;

import com.example.project.model.CourseTracker;
import com.example.project.repository.CourseTrackerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseTrackerRepository courseTrackerRepository;

    public CourseService(CourseTrackerRepository courseTrackerRepository) {
        this.courseTrackerRepository = courseTrackerRepository;
    }

    public List<CourseTracker> getAllCourses() {
        return courseTrackerRepository.findAll();
    }

    public void addCourse(CourseTracker courseTracker) {
        courseTrackerRepository.save(courseTracker);
    }

    public CourseTracker getCourseById(Long id) {
        return courseTrackerRepository.findById(id).orElse(null);
    }

    public void updateCourse(Long id, String name, int weight) {
        CourseTracker courseTracker = courseTrackerRepository.findById(id).orElse(null);
        if (courseTracker != null) {
            courseTracker.setName(name);
            courseTracker.setWeight(weight);
            courseTrackerRepository.save(courseTracker);
        }
    }

    public void deleteCourse(Long id) {
        courseTrackerRepository.deleteById(id);
    }
}
