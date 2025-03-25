package com.example.project.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.project.repository.ProgressBarRepository;
import com.example.project.model.ProgressBar;

@Service
public class ProgressBarService {
    private final ProgressBarRepository repository;

    public ProgressBarService(ProgressBarRepository repository) {
        this.repository = repository;
    }

    public List<ProgressBar> getAllProgress() {
        return repository.findAll();
    }

    public long getCompletedCoursesCount() {
        return repository.findAll().stream()
                .filter(course -> course.getProgressPercentage() == 100) // Fully completed courses
                .count();
    }

    public long getOngoingCoursesCount() {
        return repository.findAll().stream()
                .filter(course -> course.getProgressPercentage() < 100) // Courses still in progress
                .count();
    }
}
