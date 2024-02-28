package com.example.restaurant_spring.repositories;

import com.example.restaurant_spring.entities.User;
import com.example.restaurant_spring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);
     User findFirstByUserRole(UserRole role);
}
