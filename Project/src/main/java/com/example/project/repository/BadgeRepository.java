package com.example.project.repository;

import com.example.project.model.Badge;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    // finds all badges earned by a specific user
    List<Badge> findByUser(User user);

    // checks if a user has a specific badge
    boolean existsByUserAndName(User user, String badgeName);
}