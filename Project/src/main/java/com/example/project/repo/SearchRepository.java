package com.example.project.repo;
// Imported packages
import com.example.project.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
// SearchRepository interface
public interface SearchRepository extends JpaRepository<SearchEntity, Long> {
    // Method to find by name or description
    List<SearchEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword1, String keyword2);
    // Method to find by category
    List<SearchEntity> findByCategoryIgnoreCase(String category);
}
