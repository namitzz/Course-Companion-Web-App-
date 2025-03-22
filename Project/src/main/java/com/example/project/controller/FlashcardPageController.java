package com.example.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlashcardPageController {

    @GetMapping({"/flashcard"})
    public String showPage(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirect if not logged in
        }

        return "index";
    }

    @GetMapping({"/create"})
    public String createPage() {
        return "create";
    }

    @GetMapping({"/play"})
    public String playPage() {
        return "play";
    }
}
