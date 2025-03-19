package com.example.project.service;

import com.example.project.model.Flashcard;
import com.example.project.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashcardService {

    private final FlashcardRepository repository;

    public FlashcardService(FlashcardRepository repository) {
        this.repository = repository;
    }

    public List<Flashcard> getAllFlashcards() {
        return repository.findAll();
    }

    public Flashcard createFlashcard(Flashcard card) {
        return repository.save(card);
    }

    public void resetFlashcards() {
        repository.deleteAll();
    }
}
