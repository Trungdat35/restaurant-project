package com.example.restaurant_spring.controllers;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.example.restaurant_spring.dtos.ReservationDto;
import com.example.restaurant_spring.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = customerService.getAllCategories();
        if (categoryDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtos);
    }
    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByTitle(@PathVariable String title) {
        List<CategoryDto> categoryDtos = customerService.getCategoriesByTitle(title);
        if (categoryDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtos);
    }
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductByCategory(@PathVariable Long categoryId) {
        List<ProductDto> productDtos = customerService.getAllProductByCategory(categoryId);
        if (productDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtos);
    }
    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title) {
        List<ProductDto> productDtos = customerService.getProductByCategoryAndTitle(categoryId, title);
        if (productDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtos);
    }
    @PostMapping("/reservation")
    public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationDto) throws IOException {
        ReservationDto createReservationDto = customerService.postReservation(reservationDto);
        if (createReservationDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createReservationDto);
    }
    @GetMapping("/reservation/{customerId}")
    public ResponseEntity<List<ReservationDto>> getReservationByUser(@PathVariable Long customerId) {
        List<ReservationDto> reservationDtos = customerService.getReservationByUser(customerId);
        if (reservationDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtos);
    }
}
