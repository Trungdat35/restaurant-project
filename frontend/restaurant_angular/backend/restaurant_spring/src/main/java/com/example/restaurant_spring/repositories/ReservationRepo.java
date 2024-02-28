package com.example.restaurant_spring.repositories;

import com.example.restaurant_spring.entities.Reseration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reseration, Long> {
    List<Reseration> findAllByUserUserId(Long customeId);
}
