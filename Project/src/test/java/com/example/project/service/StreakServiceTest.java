package com.example.project.service;

import com.example.project.model.Streak;
import com.example.project.model.User;
import com.example.project.repository.StreakRepository;
import com.example.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StreakServiceTest {

    @Mock
    private StreakRepository streakRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private StreakService streakService;

    private User user;
    private Streak streak;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);

        streak = new Streak();
        streak.setUserId(1L);
        streak.setStreakCount(4);
        streak.setLastActivityDate(LocalDate.now().minusDays(1));
        streak.setMysteryBoxAvailable(false);
    }

    @Test
    void testGetUserStreakExisting() {
        when(streakRepository.findByUserId(1L)).thenReturn(Optional.of(streak));

        Streak result = streakService.getUserStreak(1L);

        assertNotNull(result);
        assertEquals(4, result.getStreakCount());
        verify(streakRepository).findByUserId(1L);
    }

    @Test
    void testGetUserStreakNew() {
        when(streakRepository.findByUserId(2L)).thenReturn(Optional.empty());

        Streak result = streakService.getUserStreak(2L);

        assertNotNull(result);
        assertEquals(0, result.getStreakCount());
        assertEquals(2L, result.getUserId());
    }

    @Test
    void testUpdateStreakConsecutive() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(streakRepository.findByUserId(1L)).thenReturn(Optional.of(streak));
        when(streakRepository.save(any(Streak.class))).thenReturn(streak);

        Streak result = streakService.updateStreak(1L);

        assertEquals(5, result.getStreakCount());
        assertTrue(result.isMysteryBoxAvailable());
        verify(streakRepository).save(any(Streak.class));
    }

    @Test
    void testUpdateStreakNotConsecutive() {
        streak.setLastActivityDate(LocalDate.now().minusDays(3));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(streakRepository.findByUserId(1L)).thenReturn(Optional.of(streak));
        when(streakRepository.save(any(Streak.class))).thenReturn(streak);

        Streak result = streakService.updateStreak(1L);

        assertEquals(1, result.getStreakCount());
        assertFalse(result.isMysteryBoxAvailable());
        verify(streakRepository).save(any(Streak.class));
    }

    @Test
    void testClaimMysteryBoxAvailable() {
        streak.setMysteryBoxAvailable(true);
        when(streakRepository.findByUserId(1L)).thenReturn(Optional.of(streak));
        when(streakRepository.save(any(Streak.class))).thenReturn(streak);

        String response = streakService.claimMysteryBox(1L);

        assertTrue(response.startsWith("Congratulations! You won a:"));
        assertFalse(streak.isMysteryBoxAvailable());
        verify(streakRepository).save(any(Streak.class));
    }

    @Test
    void testClaimMysteryBoxUnavailable() {
        streak.setMysteryBoxAvailable(false);
        when(streakRepository.findByUserId(1L)).thenReturn(Optional.of(streak));

        String response = streakService.claimMysteryBox(1L);

        assertEquals("No mystery box available. Keep your streak going!", response);
        verify(streakRepository, never()).save(any(Streak.class));
    }
}
