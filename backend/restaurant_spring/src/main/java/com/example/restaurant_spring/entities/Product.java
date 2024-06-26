package com.example.restaurant_spring.entities;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private double price;
    private String description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "category_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Category category;
    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "categoryId",foreignKey = @ForeignKey(name = "fk_category_product"))
    private Category category;

    public ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);
        productDto.setPrice(price);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setReturnedImg(img);
        productDto.setCategoryId(category.getCategoryId());
        productDto.setCategoryName(category.getName());
        return productDto;
    }

}
