package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Controller
public class ProgressBarViewController {

    @GetMapping("/progressbar")
    public String showProgress(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/api/progressbar";
        List<?> progressData = restTemplate.getForObject(apiUrl, List.class);

        model.addAttribute("courses", progressData);
        return "progressbar"; // Loads progressbar.html
    }
}
