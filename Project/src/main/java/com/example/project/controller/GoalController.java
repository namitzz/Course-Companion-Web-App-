package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.service.GoalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/goals") // Base mapping for all goal-related endpoints
public class GoalController {

    private final GoalService goalService;
    private final UserRepository userRepository; // Inject UserRepository to fetch user details

    public GoalController(GoalService goalService, UserRepository userRepository) {
        this.goalService = goalService;
        this.userRepository = userRepository;
    }

    /**
     * Handles the retrieval of all goals associated with the currently authenticated user.
     * Categorizes goals into active, expired, and completed.
     *
     * @param principal Represents the currently logged-in user.
     * @param model Holds attributes for rendering the view.
     * @return The Thymeleaf template "goals.html" displaying goal lists.
     */
    @GetMapping
    public String getGoals(Principal principal, Model model) {
        User user = getUserFromPrincipal(principal); // Get user from database
        model.addAttribute("activeGoals", goalService.getActiveGoals(user));
        model.addAttribute("expiredGoals", goalService.getExpiredGoals(user));
        model.addAttribute("completedGoals", goalService.getCompletedGoals(user));
        return "goals";
    }

    // Displays the form for adding a new goal.
    @GetMapping("/addGoal")
    public String showAddGoalForm() {
        return "addGoal"; // Renders addGoal form view
    }

    /**
     * Processes the form submission for adding a new goal.
     *
     * @param principal Represents the logged-in user.
     * @param title Goal title.
     * @param days Number of days to achieve the goal.
     * @param hours Number of hours to achieve the goal.
     * @param minutes Number of minutes to achieve the goal.
     * @return Redirects back to the goals list after adding the goal.
     */
    @PostMapping("/add")
    public String addGoal(Principal principal, @RequestParam String title,
                          @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        User user = getUserFromPrincipal(principal); // Get user from database
        goalService.addGoal(user, title, days, hours, minutes); // Calls service method to save goal
        return "redirect:/goals"; // Redirect to goals page after adding
    }

    /**
     * Displays the edit goal form pre-filled with existing goal details.
     *
     * @param id Goal ID to be edited.
     * @param model Holds attributes to pass data to the view.
     * @return The Thymeleaf template "editGoal.html".
     */
    @GetMapping("/edit/{id}")
    public String showEditGoalForm(@PathVariable Long id, Model model) {
        goalService.getGoalById(id).ifPresent(goal -> {
            int[] timeComponents = goalService.calculateTimeComponents(goal);
            model.addAttribute("goal", goal);
            model.addAttribute("days", timeComponents[0]);
            model.addAttribute("hours", timeComponents[1]);
            model.addAttribute("minutes", timeComponents[2]);
        });
        return "editGoal"; // Renders edit goal form view
    }

    /**
     * Handles the submission of the goal edit form.
     *
     * @param id Goal ID to be updated.
     * @param title Updated goal title.
     * @param days Updated number of days.
     * @param hours Updated number of hours.
     * @param minutes Updated number of minutes.
     * @return Redirects to goals list after updating.
     */
    @PostMapping("/{id}/edit")
    public String editGoal(@PathVariable Long id, @RequestParam String title,
                           @RequestParam int days, @RequestParam int hours, @RequestParam int minutes) {
        goalService.editGoal(id, title, days, hours, minutes); // Updates goal in database
        return "redirect:/goals"; // Redirect to goals page after edit
    }

    /**
     * Marks a goal as complete.
     *
     * @param id ID of the goal to be marked complete.
     * @return Redirects back to the goals list.
     */
    @PostMapping("/{id}/complete") // Using POST for updating state
    public String completeGoal(@PathVariable Long id) {
        goalService.completeGoal(id); // Calls service to mark goal as completed
        return "redirect:/goals"; // Redirect to goals page
    }

    /**
     * Deletes a goal from the database.
     *
     * @param id ID of the goal to be deleted.
     * @return Redirects back to the goals list.
     */
    @PostMapping("/{id}/delete") // Using POST to delete a resource
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id); // Calls service to delete goal
        return "redirect:/goals"; // Redirect to goals page
    }

    /**
     * Helper method to fetch the authenticated user from the database.
     *
     * @param principal The security principal representing the logged-in user.
     * @return The User entity retrieved from the database.
     * @throws RuntimeException if the user is not found.
     */
    private User getUserFromPrincipal(Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}