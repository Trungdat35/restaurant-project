package com.example.restaurant_spring.exception;

import com.example.restaurant_spring.entities.Product;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private HttpStatus httpStatus;
    private String message;
    private Object data;
}
