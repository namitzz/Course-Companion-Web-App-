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
}
