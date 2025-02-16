package com.example.project.controller;

import com.example.project.entity.SearchEntity;
import com.example.project.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private FilterService filterService;

    @GetMapping("/search")
    public String searchResults(@RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String category,
                                Model model) {
        List<SearchEntity> results = (keyword != null || category != null)
                ? filterService.filterResults(keyword, category)
                : List.of();

        model.addAttribute("results", results);
        return "search";
    }
}
