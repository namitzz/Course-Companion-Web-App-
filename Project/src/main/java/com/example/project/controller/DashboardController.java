package com.example.project.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Add the logged-in username to the model
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        return "dashboard";
    }
}
