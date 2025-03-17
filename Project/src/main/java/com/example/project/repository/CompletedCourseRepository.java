package com.example.project.repository;

import com.example.project.model.CompletedCourse;
import com.example.project.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedCourseRepository extends JpaRepository<CompletedCourse, Long> {

    List<CompletedCourse> findByUser(User user);


    boolean existsByUserAndCourseId(User user, Long courseId);


    @Query("SELECT c.course.title FROM CompletedCourse c " +
            "GROUP BY c.course.title " +
            "ORDER BY COUNT(c.course.id) DESC")
    List<String> findTop3PopularCourses(Pageable pageable);
}
