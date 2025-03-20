package com.example.project.controller;

import com.example.project.model.Flashcard;
import com.example.project.service.FlashcardService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final FlashcardService service;
    private final List<Flashcard> gameDeck = new ArrayList<>();
    private int currentIndex = 0;

    public GameController(FlashcardService service) {
        this.service = service;
    }

    @PostMapping("/start")
    public void startGame() {
        gameDeck.clear();
        gameDeck.addAll(service.getAllFlashcards());
        Collections.shuffle(gameDeck);
        currentIndex = 0;
    }

    @GetMapping("/next")
    public Flashcard nextQuestion() {
        if (currentIndex < gameDeck.size()) {
            return gameDeck.get(currentIndex++);
        } else {
            return null; // No more questions
        }
    }

    @GetMapping("/remaining")
    public int remainingQuestions() {
        return gameDeck.size() - currentIndex;
    }
}
