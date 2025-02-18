package com.example.project.controller;

import com.example.project.model.UserInfo;
import com.example.project.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInfoRepository = userInfoRepository;
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
        if (userInfoRepository.findByUsername(username).isPresent()) {
            return "redirect:/auth/register?error=username_exists";  // Redirect with error message
        }

        UserInfo newUserInfo = new UserInfo(username, passwordEncoder.encode(password), Set.of("ROLE_USER"));
        userInfoRepository.save(newUserInfo);
        return "redirect:/auth/login?success=registration_successful";  //
    }

    // Handle Login Form Submission (POST Request)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<UserInfo> userOptional = userInfoRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return "redirect:/auth/login?error=user_not_found";  // Redirect with error message
        }

        UserInfo userInfo = userOptional.get();
        if (!passwordEncoder.matches(password, userInfo.getPassword())) {
            return "redirect:/auth/login?error=invalid_password";  // Redirect with error message
        }

        return "redirect:/dashboard";  // Redirect to dashboard after successful login
    }
}