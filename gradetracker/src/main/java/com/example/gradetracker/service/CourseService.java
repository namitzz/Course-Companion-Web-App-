package com.example.gradetracker.service;

import com.example.gradetracker.model.Course;
import com.example.gradetracker.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void updateCourse(Long id, String name, int weight) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setName(name);
            course.setWeight(weight);
            courseRepository.save(course);
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
