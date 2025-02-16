package com.example.project.controller;

import com.example.project.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @PostMapping("/{userId}/complete-course/{courseId}")
    public void completeCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        badgeService.completeCourse(userId, courseId);
    }
}