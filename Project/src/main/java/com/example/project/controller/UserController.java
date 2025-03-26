package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Constructor Injection (Best Practice)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users-xp")
    public String showAllUsersXp(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login"; // Redirect if no user is logged in
        }

        // Retrieve all users
        List<User> users = userService.getAllUsers();

        // Sort users by XP in descending order
        users.sort((u1, u2) -> Integer.compare(u2.getXp(), u1.getXp())); // Sorting in descending order

        System.out.println("Sorted Users: " + users); // Optional: Log the sorted users for debugging

        // Add the sorted users list to the model for Thymeleaf
        model.addAttribute("users", users);

        return "all-users-xp"; // Return the Thymeleaf template
    }
}
