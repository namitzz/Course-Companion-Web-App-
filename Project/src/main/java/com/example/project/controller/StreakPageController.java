package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StreakPageController {

    private final UserService userService;

    @Autowired
    public StreakPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/streak")
    public String showStreakPage(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // ✅ Redirect if not logged in
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            User user = userService.getUserByUsername(userDetails.getUsername());  // ✅ Fetch user properly
            model.addAttribute("userId", user.getId());  // ✅ Send `userId` to Thymeleaf
        }

        return "streak";  // ✅ Renders `streak.html`
    }
}
