package com.example.restaurant_spring.services.customer;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.example.restaurant_spring.dtos.ReservationDto;
import com.example.restaurant_spring.entities.Category;
import com.example.restaurant_spring.entities.Product;
import com.example.restaurant_spring.entities.Reseration;
import com.example.restaurant_spring.entities.User;
import com.example.restaurant_spring.enums.ReservationStatus;
import com.example.restaurant_spring.repositories.CategoryRepo;
import com.example.restaurant_spring.repositories.ProductRepo;
import com.example.restaurant_spring.repositories.ReservationRepo;
import com.example.restaurant_spring.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private  final ReservationRepo reservationRepo;
    private final UserRepo userRepo;
    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByTitle(String title) {
        return  categoryRepo.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductByCategory(Long categoryId) {
        return productRepo.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title) {
        return productRepo.findAllByCategoryIdAndNameContaining(categoryId,title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto postReservation(ReservationDto reservationDto) {
        Optional<User> user = userRepo.findById(reservationDto.getCustomerId());
        if(user.isPresent()){
            Reseration reseration = new Reseration();
            reseration.setTableType(reservationDto.getTableType());
            reseration.setDateTime(reservationDto.getDateTime());
            reseration.setDescription(reservationDto.getDescription());
            reseration.setUser(user.get());
            reseration.setReservationStatus(ReservationStatus.PENDING);
            Reseration postReseration = reservationRepo.save(reseration);
            ReservationDto postReservationDto = new ReservationDto();
            postReservationDto.setId(postReseration.getId());
            return postReservationDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservationByUser(Long customerId) {
        return reservationRepo.findAllByUserUserId(customerId).stream().map(Reseration::getReservationDto).collect(Collectors.toList());
    }
}
