package com.example.project.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.project.service.ProgressBarService;
import com.example.project.model.ProgressBar;

@RestController
@RequestMapping("/api/progressbar")
public class ProgressBarController {
    private final ProgressBarService service;

    public ProgressBarController(ProgressBarService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProgressBar> getProgress() {
        return service.getAllProgress();
    }
}
