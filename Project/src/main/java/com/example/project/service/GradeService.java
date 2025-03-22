package com.example.project.service;

import com.example.project.model.Grade;
import com.example.project.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    public Grade getGradeById(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        return grade.orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }
}