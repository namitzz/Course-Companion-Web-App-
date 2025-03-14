package com.example.project.repository;

import com.example.project.model.Streak;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
public class StreakRepositoryTest {

    @Autowired
    private StreakRepository streakRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void testFindByUserIdWhenExists() {
        // Arrange
        Streak streak = new Streak();
        streak.setUserId(100L);
        streak.setStreakCount(3);
        streak.setLastActivityDate(LocalDate.now().minusDays(1));
        streak.setMysteryBoxAvailable(false);
        entityManager.persist(streak);
        entityManager.flush();

        // Act
        Optional<Streak> found = streakRepository.findByUserId(100L);

        // Assert
        assertTrue(found.isPresent(), "Streak should exist for userId=100");
        assertEquals(3, found.get().getStreakCount());
    }

    @Test
    void testFindByUserIdWhenNotExists() {
        // Act
        Optional<Streak> found = streakRepository.findByUserId(999L);

        // Assert
        assertFalse(found.isPresent(), "Streak should not exist for userId=999");
    }

    @Test
    void testSaveStreak() {
        // Arrange
        Streak streak = new Streak();
        streak.setUserId(200L);
        streak.setStreakCount(1);
        streak.setLastActivityDate(LocalDate.now());

        // Act
        Streak savedStreak = streakRepository.save(streak);

        // Assert
        assertNotNull(savedStreak.getId(), "Streak ID should be generated");
        assertEquals(200L, savedStreak.getUserId());
    }

    @Test
    void testUpdateStreak() {
        // Arrange
        Streak streak = new Streak();
        streak.setUserId(300L);
        streak.setStreakCount(2);
        streak.setLastActivityDate(LocalDate.now().minusDays(2));
        entityManager.persist(streak);
        entityManager.flush();

        // Act
        streak.setStreakCount(3);
        streak.setLastActivityDate(LocalDate.now());
        Streak updatedStreak = streakRepository.save(streak);

        // Assert
        assertEquals(3, updatedStreak.getStreakCount());
        assertEquals(LocalDate.now(), updatedStreak.getLastActivityDate());
    }

    @Test
    void testDeleteStreak() {
        // Arrange
        Streak streak = new Streak();
        streak.setUserId(400L);
        entityManager.persist(streak);
        entityManager.flush();

        // Act
        streakRepository.delete(streak);
        Optional<Streak> deleted = streakRepository.findByUserId(400L);

        // Assert
        assertFalse(deleted.isPresent(), "Streak should be deleted successfully");
    }
}
