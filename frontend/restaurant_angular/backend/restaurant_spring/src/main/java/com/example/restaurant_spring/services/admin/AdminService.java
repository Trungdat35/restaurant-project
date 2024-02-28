package com.example.restaurant_spring.services.admin;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.example.restaurant_spring.dtos.ReservationDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AdminService {
    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByTitle(String title);

    ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

    List<ProductDto> getAllProductByCategory(Long categoryId);

    List<ProductDto> getProductByCategoryAndTitle(Long categoryId,String title);

    void deleteProduct(Long productId);
    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long categoryId, ProductDto productDto) throws IOException;

    List<ReservationDto> getReservations();

    ReservationDto changeReservationStatus(Long reservationId, String status);

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) throws IOException;

    CategoryDto getCategoryById(Long categoryId);

    void deleteCategory(Long categoryId);
}
