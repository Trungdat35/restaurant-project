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
        List<CategoryDto> categoryDto = customerService.getAllCategories();
        if (categoryDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByTitle(@PathVariable String title) {
        List<CategoryDto> categoryDto = customerService.getCategoriesByTitle(title);
        if (categoryDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductByCategory(@PathVariable Long categoryId) {
        List<ProductDto> productDto = customerService.getAllProductByCategory(categoryId);
        if (productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title) {
        List<ProductDto> productDto = customerService.getProductByCategoryAndTitle(categoryId, title);
        if (productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
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
        List<ReservationDto> reservationDto = customerService.getReservationByUser(customerId);
        if (reservationDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDto);
    }
}
