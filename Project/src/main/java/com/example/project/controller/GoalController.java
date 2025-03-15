package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.GoalService;
import com.example.project.repository.UserRepository; // Ensure this import is included
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;
    private final UserRepository userRepository; // Inject the UserRepository

    // Constructor to inject GoalService and UserRepository
    public GoalController(GoalService goalService, UserRepository userRepository) {
        this.goalService = goalService;
        this.userRepository = userRepository;
    }

    // Get all goals for the current user
    @GetMapping
    public String getGoals(Principal principal, Model model) {
        User user = getUserFromPrincipal(principal); // Get the actual user from DB
        model.addAttribute("activeGoals", goalService.getActiveGoals(user));
        model.addAttribute("expiredGoals", goalService.getExpiredGoals(user));
        model.addAttribute("completedGoals", goalService.getCompletedGoals(user));
        return "goals";
    }

    // Show form to add a new goal
    @GetMapping("/addGoal")
    public String showAddGoalForm() {
        return "addGoal"; // Ensure this points to a valid Thymeleaf template
    }

    // Add a new goal for the current user
    @PostMapping("/add")
    public String addGoal(Principal principal, @RequestParam String title,
                          @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        User user = getUserFromPrincipal(principal); // Get the actual user from DB
        goalService.addGoal(user, title, days, hours, minutes);
        return "redirect:/goals";
    }

    // Show form to edit an existing goal
    @GetMapping("/edit/{id}")
    public String showEditGoalForm(@PathVariable Long id, Model model) {
        goalService.getGoalById(id).ifPresent(goal -> model.addAttribute("goal", goal));
        return "editGoal";
    }

    // Edit an existing goal
    @PostMapping("/{id}/edit")
    public String editGoal(@PathVariable Long id, @RequestParam String title,
                           @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        // Edit the goal by calling the service method
        goalService.editGoal(id, title, days, hours, minutes);
        return "redirect:/goals"; // Redirect after the form is processed
    }

    // Mark a goal as complete
    @PostMapping("/{id}/complete") // Change to POST mapping
    public String completeGoal(@PathVariable Long id) {
        goalService.completeGoal(id);
        return "redirect:/goals";
    }

    // Delete a goal
    @PostMapping("/{id}/delete") // Change to POST mapping
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return "redirect:/goals";
    }

    // Helper method to get the current user from the Principal
    private User getUserFromPrincipal(Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
