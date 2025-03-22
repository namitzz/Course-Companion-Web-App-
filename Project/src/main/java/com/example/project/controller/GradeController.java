package com.example.project.controller;

import com.example.project.model.Grade;
import com.example.project.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/grades")
    public String viewGrades(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "grades";
    }

    @PostMapping("/grades/add")
    public String addGrade(@RequestParam Map<String, String> allParams, Model model) {
        Grade grade = new Grade();
        grade.setCourseName(allParams.get("courseName"));

        allParams.forEach((key, value) -> {
            if (key.startsWith("weight_")) {
                String component = key.replace("weight_", "");
                try {
                    double weight = Double.parseDouble(value);
                    grade.getComponentWeights().put(component, weight);

                    String gradeKey = "grade_" + component;
                    if (allParams.containsKey(gradeKey) && !allParams.get(gradeKey).isEmpty()) {
                        double gradeValue = Double.parseDouble(allParams.get(gradeKey));
                        grade.getComponentGrades().put(component, gradeValue);
                    }
                } catch (NumberFormatException ignored) {}
            }
        });

        double totalWeight = grade.getComponentWeights().values().stream().mapToDouble(Double::doubleValue).sum();

        if (Math.abs(totalWeight - 100.0) > 0.01) {
            model.addAttribute("error", "Total weight must be 100%. Current total weight: " + totalWeight + "%");
            model.addAttribute("grades", gradeService.getAllGrades());
            return "grades";
        }

        gradeService.saveGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades/delete/{id}")
    public String deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/grades";
    }

}