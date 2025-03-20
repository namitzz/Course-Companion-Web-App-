package com.example.project.service;

import com.example.project.model.Flashcard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class GameService {

    private boolean timeUp = false;

    public void startGame(List<Flashcard> flashcards, int duration) {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        if (duration > 0) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up!");
                }
            }, duration * 1000);
        }

        int correct = 0;
        for (Flashcard card : flashcards) {
            if (timeUp) break;
            System.out.println("Question: " + card.getQuestion());
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(card.getAnswer())) {
                correct++;
            }
        }

        System.out.println("Game Over! You got " + correct + " correct answers.");
        timer.cancel();
    }
}
