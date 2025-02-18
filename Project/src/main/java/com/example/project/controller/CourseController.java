package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.project.model.CourseTracker;
import com.example.project.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @PostMapping("/add")
    public String addCourse(@RequestParam String name, @RequestParam int weight) {
        courseService.addCourse(new CourseTracker(name, weight));
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        CourseTracker courseTracker = courseService.getCourseById(id);
        model.addAttribute("course", courseTracker);
        return "edit";
    }

    @PostMapping("/update")
    public String updateCourse(@RequestParam Long id, @RequestParam String name, @RequestParam int weight) {
        courseService.updateCourse(id, name, weight);
        return "redirect:/courses";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
