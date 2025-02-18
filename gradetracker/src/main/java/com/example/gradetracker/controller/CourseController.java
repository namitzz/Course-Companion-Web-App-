package com.example.gradetracker.controller;

import com.example.gradetracker.model.Course;
import com.example.gradetracker.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        courseService.addCourse(new Course(name, weight));
        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
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
