package com.example.restaurant_spring.entities;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String name;
    private String description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> productSet;
    public CategoryDto getCategoryDto(){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);
        categoryDto.setName(name);
        categoryDto.setDescription(description);
        categoryDto.setReturnedImg(img);
        return categoryDto;
    }
}
