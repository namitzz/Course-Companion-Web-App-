package com.example.project.repository;

import com.example.project.model.Goal;
import com.example.project.model.GoalStatus;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserAndStatus(User user, GoalStatus status);
}
