package com.example.restaurant_spring.repositories;

import com.example.restaurant_spring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
   List<Product> findAllByCategoryCategoryId(Long categoryId);
   List<Product> findAllByCategoryCategoryIdAndNameContaining(Long categoryId,String title);
}
