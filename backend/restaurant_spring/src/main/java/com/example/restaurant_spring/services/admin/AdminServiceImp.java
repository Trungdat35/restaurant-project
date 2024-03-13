package com.example.restaurant_spring.services.admin;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.example.restaurant_spring.dtos.ReservationDto;
import com.example.restaurant_spring.entities.Category;
import com.example.restaurant_spring.entities.Product;
import com.example.restaurant_spring.entities.Reservation;
import com.example.restaurant_spring.enums.ReservationStatus;
import com.example.restaurant_spring.repositories.CategoryRepo;
import com.example.restaurant_spring.repositories.ProductRepo;
import com.example.restaurant_spring.repositories.ReservationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
    @Autowired
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final ReservationRepo reservationRepo;

    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription((categoryDto.getDescription()));
        category.setImg(categoryDto.getImg().getBytes());
        Category createCategory = categoryRepo.save(category);
        CategoryDto creatCategoryDto = new CategoryDto();
        creatCategoryDto.setId(createCategory.getCategoryId());
        return creatCategoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTitle(String title) {
        return categoryRepo.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            product.setImg(productDto.getImg().getBytes());
            product.setCategory(optionalCategory.get());
            Product createProduct = productRepo.save(product);
            ProductDto createProductDto = new ProductDto();
            createProductDto.setId(createProduct.getProductId());
            return createProductDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProductByCategory(Long categoryId) {
        return productRepo.findAllByCategoryCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title) {
        return productRepo.findAllByCategoryCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> deleteProduct = productRepo.findById(productId);
        if (deleteProduct.isPresent()) {
            productRepo.delete(deleteProduct.get());
        } else {
            throw new IllegalArgumentException("Not found productId !");
        }

    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        return product.map(Product::getProductDto).orElse(null);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            if (productDto.getImg() != null) {
                product.setImg(productDto.getImg().getBytes());
            }
            Product updateProduct = productRepo.save(product);
            ProductDto updateProductDto = new ProductDto();
            updateProductDto.setId(updateProduct.getProductId());
            return updateProductDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservations() {
        return reservationRepo.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto changeReservationStatus(Long reservationId, String status) {

        Optional<Reservation> optionalReseration = reservationRepo.findById(reservationId);
        if (optionalReseration.isPresent()) {
            Reservation existingReseration = optionalReseration.get();
            if (Objects.equals(status, "Approve")) {
                existingReseration.setReservationStatus(ReservationStatus.APPROVED);
            } else {
                existingReseration.setReservationStatus(ReservationStatus.DISAPPROVED);
            }
            Reservation updateReseration = reservationRepo.save(existingReseration);
            ReservationDto updateReservationDto = new ReservationDto();
            updateReservationDto.setId(updateReseration.getReservationId());
            return updateReservationDto;
        }
        return null;
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            if (categoryDto.getImg() != null) {
                category.setImg(categoryDto.getImg().getBytes());
            }
            Category updateCategory = categoryRepo.save(category);
            CategoryDto updateCategoryDto = new CategoryDto();
            updateCategoryDto.setId(updateCategory.getCategoryId());
            return updateCategoryDto;
        }
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        return optionalCategory.map(Category::getCategoryDto).orElse(null);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if (optionalCategory.isPresent()) {
            categoryRepo.delete(optionalCategory.get());
        } else {
            throw new IllegalArgumentException("Not found categoryId !");
        }
    }
}
