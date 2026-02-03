package com.wApp.WeatherForcasting.controller;

import com.wApp.WeatherForcasting.model.Users;
import com.wApp.WeatherForcasting.requests.LoginRequest;
import com.wApp.WeatherForcasting.security.JwtUtil;
import com.wApp.WeatherForcasting.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class UsersController {

        @Autowired
        private UserService usersService;

        @Autowired
        private JwtUtil jwtUtil;

        @PostMapping("/addUser")
        public ResponseEntity<?> addUser(@RequestBody Users user){
                try {
                        return ResponseEntity.ok(usersService.addUser(user));
                } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                }
        }

        @PostMapping("/loginUser")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

                Users user = usersService.authenticate(loginRequest);

                // 🔐 Generate JWT
                String token = jwtUtil.generateToken(user.getEmail());

                return ResponseEntity.ok(Map.of("token", token));
        }

        // 🔐 PROTECTED WEATHER CHECK
        @GetMapping("/api/weather/check")
        public ResponseEntity<String> weatherAccessCheck() {
                return ResponseEntity.ok("AUTHORIZED");
        }


        @GetMapping("/api/user/me")
        public ResponseEntity<?> getLoggedInUser(Authentication authentication) {
                String email = authentication.getName();
                Users user = usersService.findByEmail(email);

                return ResponseEntity.ok(Map.of(
                        "name", user.getName(),
                        "email", user.getEmail()
                ));
}
}
