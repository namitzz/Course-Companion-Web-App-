package com.example.project.controller;

import com.example.project.model.Deadline;
import com.example.project.service.DeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller
public class DeadlineController {

    @Autowired
    private DeadlineService deadlineService;

    @GetMapping("/deadlines")
    public String viewDeadlines(@RequestParam(required = false) String sortBy,
                                @RequestParam(required = false) Long editId,
                                Model model) {
        List<Deadline> deadlines = deadlineService.getAllDeadlines();

        // Sorting logic
        if (sortBy != null) {
            switch (sortBy) {
                case "dateAsc":
                    deadlines.sort(Comparator.comparing(Deadline::getDueDate));
                    break;
                case "dateDesc":
                    deadlines.sort(Comparator.comparing(Deadline::getDueDate).reversed());
                    break;
                case "weightDesc":
                    deadlines.sort(Comparator.comparing(Deadline::getTaskWeight).reversed());
                    break;
            }
        }

        // If editing, add the deadline object to prefill the form
        if (editId != null) {
            Deadline editDeadline = deadlineService.getDeadlineById(editId);
            model.addAttribute("editDeadline", editDeadline);
        } else {
            model.addAttribute("editDeadline", new Deadline());
        }

        model.addAttribute("deadlines", deadlines);
        return "deadlines";
    }

    @PostMapping("/deadlines/add")
    public String addOrUpdateDeadline(@RequestParam(required = false) Long id,
                                      @RequestParam String courseName,
                                      @RequestParam String taskType,
                                      @RequestParam int taskWeight,
                                      @RequestParam String dueDate,
                                      Model model) {

        LocalDate parsedDueDate = LocalDate.parse(dueDate);
        if (parsedDueDate.isBefore(LocalDate.now())) {
            model.addAttribute("error", "Due date cannot be in the past.");
            model.addAttribute("deadlines", deadlineService.getAllDeadlines());
            model.addAttribute("editDeadline", new Deadline(id, courseName, taskType, taskWeight, parsedDueDate));
            return "deadlines";
        }

        Deadline deadline = new Deadline(id, courseName, taskType, taskWeight, parsedDueDate);
        deadlineService.saveDeadline(deadline);
        return "redirect:/deadlines";
    }

    @GetMapping("/deadlines/delete/{id}")
    public String deleteDeadline(@PathVariable Long id) {
        deadlineService.deleteDeadline(id);
        return "redirect:/deadlines";

    }

}
