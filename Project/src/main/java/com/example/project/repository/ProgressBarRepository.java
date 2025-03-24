package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.ProgressBar;

public interface ProgressBarRepository extends JpaRepository<ProgressBar, Long> { }
