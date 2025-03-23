package com.example.project.repository;

import com.example.project.model.MysteryBoxReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MysteryBoxRewardRepository extends JpaRepository<MysteryBoxReward, Long> {
    List<MysteryBoxReward> findByUserId(Long userId);
}
