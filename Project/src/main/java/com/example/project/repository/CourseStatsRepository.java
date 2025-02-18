package com.example.project.repository;

import com.example.project.entity.CourseStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStatsRepository extends JpaRepository<CourseStats, Long> { }
