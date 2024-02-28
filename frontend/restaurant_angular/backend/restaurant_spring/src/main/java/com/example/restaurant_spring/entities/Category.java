package com.example.restaurant_spring.entities;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    public CategoryDto getCategoryDto(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(id);
        categoryDto.setName(name);
        categoryDto.setDescription(description);
        categoryDto.setReturnedImg(img);
        return categoryDto;
    }
}
