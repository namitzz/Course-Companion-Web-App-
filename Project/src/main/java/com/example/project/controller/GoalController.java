package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.service.GoalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/goals") // Base endpoint for goal operations
public class GoalController {

    private final GoalService goalService;
    private final UserRepository userRepository;

    public GoalController(GoalService goalService, UserRepository userRepository) {
        this.goalService = goalService;
        this.userRepository = userRepository;
    }

    // Retrieves and categorizes goals for the logged-in user
    @GetMapping
    public String getGoals(Principal principal, Model model) {
        User user = getUserFromPrincipal(principal);
        model.addAttribute("activeGoals", goalService.getActiveGoals(user));
        model.addAttribute("expiredGoals", goalService.getExpiredGoals(user));
        model.addAttribute("completedGoals", goalService.getCompletedGoals(user));
        return "goals";
    }

    // Displays the add goal form
    @GetMapping("/addGoal")
    public String showAddGoalForm() {
        return "addGoal";
    }

    // Processes goal creation and redirects to the goals list
    @PostMapping("/add")
    public String addGoal(Principal principal, @RequestParam String title,
                          @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        goalService.addGoal(getUserFromPrincipal(principal), title, days, hours, minutes);
        return "redirect:/goals";
    }

    // Displays the edit form with existing goal details
    @GetMapping("/edit/{id}")
    public String showEditGoalForm(@PathVariable Long id, Model model) {
        goalService.getGoalById(id).ifPresent(goal -> {
            int[] timeComponents = goalService.calculateTimeComponents(goal);
            model.addAttribute("goal", goal);
            model.addAttribute("days", timeComponents[0]);
            model.addAttribute("hours", timeComponents[1]);
            model.addAttribute("minutes", timeComponents[2]);
        });
        return "editGoal";
    }

    // Handles goal updates and redirects to the goals list
    @PostMapping("/{id}/edit")
    public String editGoal(@PathVariable Long id, @RequestParam String title,
                           @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        goalService.editGoal(id, title, days, hours, minutes);
        return "redirect:/goals";
    }

    // Marks a goal as completed
    @PostMapping("/{id}/complete")
    public String completeGoal(@PathVariable Long id) {
        goalService.completeGoal(id);
        return "redirect:/goals";
    }

    // Deletes a goal
    @PostMapping("/{id}/delete")
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return "redirect:/goals";
    }

    // Retrieves the logged-in user from the database
    private User getUserFromPrincipal(Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}