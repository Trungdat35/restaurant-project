package com.example.restaurant_spring;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "API Product",
                version = "1.0.0",
                description = "This project is only learning !",
                contact = @Contact(
                        name = "Đỗ Trung Đạt",
                        email = "dotrungdat.mda@gmail.com"
                )
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8088"
                ),
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class RestaurantSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantSpringApplication.class, args);
    }

}
