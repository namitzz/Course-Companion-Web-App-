package com.example.project.service;

import com.example.project.entity.SearchEntity;
import com.example.project.repo.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterService {

    @Autowired
    private SearchRepository searchRepository;

    public List<SearchEntity> filterResults(String keyword, String category) {
        if (category != null && !category.isEmpty()) {
            return searchRepository.findByCategoryIgnoreCase(category);
        } else if (keyword != null && !keyword.isEmpty()) {
            return searchRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        } else {
            return searchRepository.findAll();
        }
    }
}
