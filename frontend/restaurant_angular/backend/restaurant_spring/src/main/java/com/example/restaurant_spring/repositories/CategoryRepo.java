package com.example.restaurant_spring.repositories;

import com.example.restaurant_spring.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findAllByNameContaining(String title);
}
