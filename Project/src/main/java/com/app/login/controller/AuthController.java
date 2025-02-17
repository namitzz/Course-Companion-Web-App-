package com.app.login.controller;

import com.app.login.model.User;
import com.app.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/auth")
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
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return "redirect:/auth/register?error=password_mismatch";
        }

        if (userRepository.findByUsername(username).isPresent()) {
            return "redirect:/auth/register?error=username_exists";
        }

        User newUser = new User(username, passwordEncoder.encode(password), Set.of("ROLE_USER"));
        userRepository.save(newUser);
        return "redirect:/auth/login?success=registration_successful";
    }

    // Handle Login Form Submission (POST Request)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return "redirect:/auth/login?error=user_not_found";
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "redirect:/auth/login?error=invalid_password";
        }

        return "redirect:/dashboard";
    }
}