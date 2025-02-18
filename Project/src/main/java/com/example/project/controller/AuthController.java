package com.example.project.controller;

import com.example.project.model.UserInfo;
import com.example.project.repository.UserInfoRepository;
import jakarta.servlet.http.HttpSession;
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
        return "register";  // Returns the "register.jsp" page
    }

    // Show Login Page (GET Request)
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Returns the "login.jsp" page
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

    // ✅ FIXED: PostMapping should be just "/login" (Not "/auth/login")
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<UserInfo> userOptional = userInfoRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            UserInfo user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("user", username);  // Store user in session
                return "redirect:/dashboard";  // Redirect to dashboard on success
            }
        }

        return "redirect:/auth/login?error=true";  // Redirect back to login with error
    }
}
