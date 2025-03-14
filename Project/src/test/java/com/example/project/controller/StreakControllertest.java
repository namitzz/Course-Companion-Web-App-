package com.example.project.controller;

import com.example.project.model.Streak;
import com.example.project.service.StreakService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StreakController.class)
class StreakControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StreakService streakService;

    @InjectMocks
    private StreakController streakController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(streakController).build();
    }

    @Test
    void testGetUserStreak() throws Exception {
        Streak streak = new Streak();
        streak.setUserId(1L);
        streak.setStreakCount(4);
        streak.setLastActivityDate(LocalDate.now());

        when(streakService.getUserStreak(1L)).thenReturn(streak);

        mockMvc.perform(get("/api/streaks/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.streakCount").value(4));
    }

    @Test
    void testClaimMysteryBox() throws Exception {
        when(streakService.claimMysteryBox(1L))
                .thenReturn("Congratulations! You won a: Bonus XP");

        mockMvc.perform(post("/api/streaks/{userId}/claim-mystery-box", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Congratulations! You won a: Bonus XP"));
    }
}
