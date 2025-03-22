package com.example.project.service;

import com.example.project.model.Deadline;
import com.example.project.repository.DeadlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeadlineService {

    @Autowired
    private DeadlineRepository deadlineRepository;

    public List<Deadline> getAllDeadlines() {
        return deadlineRepository.findAll();
    }

    public Deadline saveDeadline(Deadline deadline) {
        return deadlineRepository.save(deadline);
    }

    public void deleteDeadline(Long id) {
        deadlineRepository.deleteById(id);
    }

    public Deadline getDeadlineById(Long id) {
        return deadlineRepository.findById(id).orElse(null);
    }
}
