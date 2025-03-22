package com.example.project.repository;

import com.example.project.model.Flashcard;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    List<Flashcard> findByUser(User user); // Query flashcards by user
}
