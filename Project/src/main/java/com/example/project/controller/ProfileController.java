package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/edit")
    public String showEditProfilePage(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
            user.ifPresent(value -> model.addAttribute("user", value));
        }
        return "edit-profile";
    }

    @PostMapping("/edit")
    public String updateProfile(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String gender,
                                Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setUsername(name);
                user.setEmail(email);
                user.setGender(gender);

                userRepository.save(user);
                return "redirect:/dashboard?success=profile_updated";
            }
        }
        return "redirect:/login";
    }
}