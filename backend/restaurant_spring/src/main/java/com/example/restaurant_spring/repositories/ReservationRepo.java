package com.example.restaurant_spring.repositories;

import com.example.restaurant_spring.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserUserId(Long customeId);
}
