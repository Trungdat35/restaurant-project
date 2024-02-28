package com.example.restaurant_spring.dtos;

import com.example.restaurant_spring.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
      private String jwt;
      private UserRole userRole;
      private Long userId;
}
