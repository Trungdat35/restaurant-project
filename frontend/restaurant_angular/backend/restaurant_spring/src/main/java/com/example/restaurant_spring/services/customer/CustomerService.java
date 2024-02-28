package com.example.restaurant_spring.services.customer;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.example.restaurant_spring.dtos.ReservationDto;

import java.util.List;

public interface CustomerService {
    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoriesByTitle(String title);

    List<ProductDto> getAllProductByCategory(Long categoryId);

    List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title);

    ReservationDto postReservation(ReservationDto reservationDto);

    List<ReservationDto> getReservationByUser(Long customerId);
}
