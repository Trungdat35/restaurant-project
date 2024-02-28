package com.example.restaurant_spring.services.auth;

import com.example.restaurant_spring.dtos.SignupRequest;
import com.example.restaurant_spring.dtos.UserDto;
import com.example.restaurant_spring.entities.User;
import com.example.restaurant_spring.enums.UserRole;
import com.example.restaurant_spring.repositories.ReservationRepo;
import com.example.restaurant_spring.repositories.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private  final ReservationRepo reservationRepo;
    private final UserRepo userRepo;
    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepo.findFirstByUserRole(UserRole.ADMIN);
        if (adminAccount == null) {
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@gmail.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepo.save(user);
        }
    }

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        if(signupRequest.getName().equals("") || (signupRequest.getPassword().equals("")) || (signupRequest.getEmail().equals(""))){
          return null;
        }
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createUser = userRepo.save(user);
        UserDto createUserDto = new UserDto();
        createUserDto.setId(createUser.getUserId());
        createUserDto.setName(createUser.getName());
        createUserDto.setEmail(createUser.getEmail());
        createUserDto.setUserRole(createUser.getUserRole());
        return createUserDto;
    }
}
