package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Show Register Page (GET Request)
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // Return the "register.jsp" page
    }

    // Show Login Page (GET Request)
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Return the "login.jsp" page
    }

    // Handle Register Form Submission (POST Request)
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "redirect:/register?error=username_exists";  // Redirect with error message
        }

        User newUser = new User(username, passwordEncoder.encode(password), Set.of("ROLE_USER"));
        userRepository.save(newUser);
        return "redirect:/login?success=registration_successful";  // Redirect to login page after successful registration
    }
}