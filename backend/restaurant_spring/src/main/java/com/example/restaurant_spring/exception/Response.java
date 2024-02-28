package com.example.restaurant_spring.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

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
