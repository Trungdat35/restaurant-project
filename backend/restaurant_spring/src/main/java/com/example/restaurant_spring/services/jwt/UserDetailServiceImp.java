package com.example.restaurant_spring.services.jwt;

import com.example.restaurant_spring.entities.User;
import com.example.restaurant_spring.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImp implements UserDetailsService {
    private  final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findFirstByEmail(email);
        if(optionalUser.isEmpty()) throw  new UsernameNotFoundException("User not found",null);
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),optionalUser.get().getPassword(), new ArrayList<>());
    }
}
