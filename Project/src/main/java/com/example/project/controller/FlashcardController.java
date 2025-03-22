package com.example.project.controller;

import com.example.project.model.Flashcard;
import com.example.project.model.User;
import com.example.project.repository.FlashcardRepository;
import com.example.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Allow frontend access from any origin
@RestController
@RequestMapping("/api")
public class FlashcardController {

    private final FlashcardRepository repository;
    private final UserService userService;

    public FlashcardController(FlashcardRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    // GET all flashcards for the currently logged-in user
    @GetMapping("/flashcards")
    public List<Flashcard> getAllFlashcards(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // User not authenticated, return null or empty list
        }

        User user = userService.getUserByUsername(authentication.getName());
        return repository.findByUser(user);
    }

    // POST new flashcard for the currently logged-in user
    @PostMapping("/flashcards")
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null; // User not authenticated
        }

        User user = userService.getUserByUsername(authentication.getName());
        flashcard.setUser(user); // Associate the flashcard with the current user
        return repository.save(flashcard);
    }

    // DELETE flashcard by ID for the currently logged-in user
    @DeleteMapping("/flashcards/{id}")
    public void deleteFlashcard(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return; // User not authenticated
        }

        User user = userService.getUserByUsername(authentication.getName());
        Flashcard flashcard = repository.findById(id).orElse(null);

        if (flashcard != null && flashcard.getUser().equals(user)) {
            repository.delete(flashcard);
        }
    }
}
