package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradePageController {
    @GetMapping("/gradetracker")
    public String showPage() {
        return "GradeTrackerindex";
    }
}