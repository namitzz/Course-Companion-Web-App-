package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project.service.ProgressBarService;
import java.util.List;

@Controller
public class ProgressBarViewController {
    private final ProgressBarService progressBarService;

    public ProgressBarViewController(ProgressBarService progressBarService) {
        this.progressBarService = progressBarService;
    }

    @GetMapping("/progressbar")
    public String showProgress(Model model) {
        List<?> progressData = progressBarService.getAllProgress();
        long completedCourses = progressBarService.getCompletedCoursesCount();
        long ongoingCourses = progressBarService.getOngoingCoursesCount();

        model.addAttribute("courses", progressData);
        model.addAttribute("completedCourses", completedCourses); // ✅ Completed count
        model.addAttribute("ongoingCourses", ongoingCourses); // ✅ Ongoing count

        return "progressbar"; // Loads progressbar.html
    }
}
