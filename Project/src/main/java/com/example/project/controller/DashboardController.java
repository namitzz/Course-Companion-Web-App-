package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String dashboardPage(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login"; // Redirect if not authenticated
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            // ✅ Get real user from database using username
            User user = userService.getUserByUsername(userDetails.getUsername());
            model.addAttribute("userId", user.getId());
        }

        return "dashboard";
    }
}
