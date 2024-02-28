package com.example.restaurant_spring.services.customer;

import com.example.restaurant_spring.dtos.CategoryDto;
import com.example.restaurant_spring.dtos.ProductDto;
import com.example.restaurant_spring.dtos.ReservationDto;
import com.example.restaurant_spring.entities.Category;
import com.example.restaurant_spring.entities.Product;
import com.example.restaurant_spring.entities.Reservation;
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
        return productRepo.findAllByCategoryCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title) {
        return productRepo.findAllByCategoryCategoryIdAndNameContaining(categoryId,title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto postReservation(ReservationDto reservationDto) {
        Optional<User> user = userRepo.findById(reservationDto.getCustomerId());
        if(user.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setTableType(reservationDto.getTableType());
            reservation.setDateTime(reservationDto.getDateTime());
            reservation.setDescription(reservationDto.getDescription());
            reservation.setUser(user.get());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            Reservation postReservation = reservationRepo.save(reservation);
            ReservationDto postReservationDto = new ReservationDto();
            postReservationDto.setId(postReservation.getReservationId());
            return postReservationDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservationByUser(Long customerId) {
        return reservationRepo.findAllByUserUserId(customerId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }
}
