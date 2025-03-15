package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.GoalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public String getGoals(Principal principal, Model model) {
        User user = getUserFromPrincipal(principal);
        model.addAttribute("activeGoals", goalService.getActiveGoals(user));
        model.addAttribute("expiredGoals", goalService.getExpiredGoals(user));
        model.addAttribute("completedGoals", goalService.getCompletedGoals(user));
        return "goals";
    }

    @GetMapping("/addGoal")
    public String showAddGoalForm() {
        return "addGoal";
    }

    @PostMapping("/add")
    public String addGoal(Principal principal, @RequestParam String title,
                          @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        User user = getUserFromPrincipal(principal);
        goalService.addGoal(user, title, days, hours, minutes);
        return "redirect:/goals";
    }

    @GetMapping("/edit/{id}")
    public String showEditGoalForm(@PathVariable Long id, Model model) {
        goalService.getGoalById(id).ifPresent(goal -> model.addAttribute("goal", goal));
        return "editGoal";
    }

    @PostMapping("/{id}/edit")
    public String editGoal(@PathVariable Long id, @RequestParam String title,
                           @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        goalService.editGoal(id, title, days, hours, minutes);
        return "redirect:/goals";
    }

    @PatchMapping("/{id}/complete")
    public String completeGoal(@PathVariable Long id) {
        goalService.completeGoal(id);
        return "redirect:/goals";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return "redirect:/goals";
    }

    private User getUserFromPrincipal(Principal principal) {
        return new User(principal.getName(), "", null);
    }
}
