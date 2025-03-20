package com.example.project.repository;

import com.example.project.model.Goal;
import com.example.project.model.GoalStatus;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Goal entities.
 * This interface provides database operations for the Goal entity.
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    /**
     * Retrieves a list of goals for a specific user and goal status.
     *
     * @param user   The user whose goals are being fetched.
     * @param status The status of the goals (ACTIVE, COMPLETED, EXPIRED).
     * @return A list of goals matching the given user and status.
     */
    List<Goal> findByUserAndStatus(User user, GoalStatus status);
}
