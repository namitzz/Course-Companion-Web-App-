package com.example.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_streaks")
public class Streak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;  //

    private int streakCount = 0;
    private LocalDate lastActivityDate;
    private boolean mysteryBoxClaimed;

    public boolean isMysteryBoxAvailable() {
        return !mysteryBoxClaimed;
    }

    public void setMysteryBoxAvailable(boolean available) {
        this.mysteryBoxClaimed = !available;
    }
}
