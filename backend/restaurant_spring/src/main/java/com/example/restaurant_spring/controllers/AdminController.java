package com.example.restaurant_spring.controllers;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.example.restaurant_spring.dtos.ReservationDto;
import com.example.restaurant_spring.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto createCategoryDto = adminService.postCategory(categoryDto);
        if (createCategoryDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createCategoryDto);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto updateCategoryDto = adminService.updateCategory(categoryId, categoryDto);
        if (updateCategoryDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateCategoryDto);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        CategoryDto categoryDto = adminService.getCategoryById(categoryId);
        if (categoryDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        adminService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDto = adminService.getAllCategories();
        if (categoryDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByTitle(@PathVariable String title) {
        List<CategoryDto> categoryDto = adminService.getAllCategoriesByTitle(title);
        if (categoryDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping("/{categoryId}/product")
    public ResponseEntity<?> postProduct(@PathVariable Long categoryId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto createProductDto = adminService.postProduct(categoryId, productDto);
        if (createProductDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createProductDto);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductByCategory(@PathVariable Long categoryId) {
        List<ProductDto> productDto = adminService.getAllProductByCategory(categoryId);
        if (productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title) {
        List<ProductDto> productDto = adminService.getProductByCategoryAndTitle(categoryId, title);
        if (productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        adminService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDto = adminService.getProductById(productId);
        if (productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto updateProductDto = adminService.updateProduct(productId, productDto);
        if (updateProductDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong !");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(updateProductDto);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getReservations() {
        List<ReservationDto> reservationDtos = adminService.getReservations();
        if (reservationDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtos);
    }

    @GetMapping("/reservation/{reservationId}/{status}")
    public ResponseEntity<ReservationDto> changeReservationStatus(@PathVariable Long reservationId, @PathVariable String status) {
        ReservationDto updateReservationDto = adminService.changeReservationStatus(reservationId, status);
        if (updateReservationDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updateReservationDto);
    }
}
