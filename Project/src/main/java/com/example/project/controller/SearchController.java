package com.example.project.controller;

// Imported packages
import com.example.project.model.SearchEntity;
import com.example.project.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class SearchController {

    // Autowired FilterService
    @Autowired
    private FilterService filterService;

    // Handles GET request for /search
    @GetMapping("/search")
    // Method to search for results
    public String searchResults(@RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String category,
                                Model model) {
        List<SearchEntity> results = (keyword != null || category != null)
                ? filterService.filterResults(keyword, category)
                : List.of();

        // Add results to model
        model.addAttribute("results", results);
        // Return search.html
        return "search";
    }
}
