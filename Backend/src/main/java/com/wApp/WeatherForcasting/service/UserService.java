package com.wApp.WeatherForcasting.service;

import com.wApp.WeatherForcasting.model.Users;
import com.wApp.WeatherForcasting.repository.UsersRepo;
import com.wApp.WeatherForcasting.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Users addUser(Users user){
        //Validate input
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email cannot be empty");
        }

        //Check duplicate email
        if (usersRepo.existsById(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        //HASH PASSWORD
        user.setPassword(encoder.encode(user.getPassword()));
        return usersRepo.save(user);
    }

    public Users authenticate(LoginRequest loginRequest){

        Users user = usersRepo.findById(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));


        // ✅ CORRECT WAY (BCrypt comparison)
        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }

    public Users findByEmail(String email) {
        return usersRepo.findById(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
