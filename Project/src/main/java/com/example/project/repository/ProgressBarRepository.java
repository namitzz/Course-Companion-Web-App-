package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.ProgressBar;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressBarRepository extends JpaRepository<ProgressBar, Long> { }
