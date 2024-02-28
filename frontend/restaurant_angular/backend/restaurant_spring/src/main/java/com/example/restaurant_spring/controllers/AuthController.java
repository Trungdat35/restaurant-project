package com.example.restaurant_spring.controllers;

import com.example.restaurant_spring.dtos.AuthenticationRequest;
import com.example.restaurant_spring.dtos.AuthenticationResponse;
import com.example.restaurant_spring.dtos.SignupRequest;
import com.example.restaurant_spring.dtos.UserDto;
import com.example.restaurant_spring.entities.User;
import com.example.restaurant_spring.exception.AllException;
import com.example.restaurant_spring.exception.Response;
import com.example.restaurant_spring.repositories.UserRepo;
import com.example.restaurant_spring.services.auth.AuthService;
import com.example.restaurant_spring.services.jwt.UserDetailServiceImp;
import com.example.restaurant_spring.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Builder
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImp userDetailServiceImp;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @PostMapping("/signup")
    public ResponseEntity<Response> signupUser(@RequestBody SignupRequest signupRequest) {
        UserDto creatUserDto = authService.createUser(signupRequest);
        if (creatUserDto == null) {
            throw new AllException("User not created. Come again later");
        }
        return ResponseEntity.ok(Response.builder().httpStatus(HttpStatus.CREATED).message("Success\", \"You're registered successfully !").data(creatUserDto).build());
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest request, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Inconnect username or password");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }
        final UserDetails userDetails = userDetailServiceImp.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        Optional<User> optionalUser = userRepo.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getUserId());
        }
        return authenticationResponse;
    }
}
