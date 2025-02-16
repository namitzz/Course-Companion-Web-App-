package com.example.project.repo;

import com.example.project.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SearchRepository extends JpaRepository<SearchEntity, Long> {
    List<SearchEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword1, String keyword2);
    List<SearchEntity> findByCategoryIgnoreCase(String category);
}
