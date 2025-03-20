package com.example.project.controller;

import com.example.project.model.Flashcard;
import com.example.project.repository.FlashcardRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Allow frontend access from any origin
@RestController
@RequestMapping("/api")
public class FlashcardController {

    private final FlashcardRepository repository;

    public FlashcardController(FlashcardRepository repository) {
        this.repository = repository;
    }

    // GET all flashcards
    @GetMapping("/flashcards")
    public List<Flashcard> getAllFlashcards() {
        return repository.findAll();
    }

    // POST new flashcard
    @PostMapping("/flashcards")
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard) {
        return repository.save(flashcard);
    }

    // DELETE flashcard by ID
    @DeleteMapping("/flashcards/{id}")
    public void deleteFlashcard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
