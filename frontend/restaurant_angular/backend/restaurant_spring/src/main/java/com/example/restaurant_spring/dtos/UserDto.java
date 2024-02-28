package com.example.restaurant_spring.dtos;

import com.example.restaurant_spring.enums.UserRole;
import lombok.Data;

@Data

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
}
