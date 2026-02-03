package com.wApp.WeatherForcasting.controller;


import com.wApp.WeatherForcasting.service.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    // PROTECTED ENDPOINT
    @GetMapping("/{city}")
    public ResponseEntity<Map<String, Object>> getWeather(@PathVariable String city, HttpServletRequest request) {

        // Get logged-in user email (set by JwtFilter)
        String email = request.getUserPrincipal().getName();

        //Fetch weather + save search history
        try {
            return ResponseEntity.ok(
                    weatherService.getWeatherAndSave(city, email)
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }


}
