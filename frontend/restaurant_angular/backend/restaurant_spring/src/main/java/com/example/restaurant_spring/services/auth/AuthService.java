package com.example.restaurant_spring.services.auth;

import com.example.restaurant_spring.dtos.SignupRequest;
import com.example.restaurant_spring.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
