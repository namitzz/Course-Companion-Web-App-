package com.example.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mystery_box_rewards")
public class MysteryBoxReward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String reward;
}
