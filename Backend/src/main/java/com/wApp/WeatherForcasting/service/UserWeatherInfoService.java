package com.wApp.WeatherForcasting.service;

import com.wApp.WeatherForcasting.model.UserWeatherInfo;
import com.wApp.WeatherForcasting.repository.UserWeatherInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserWeatherInfoService {
    @Autowired
    private UserWeatherInfoRepo repo;

    public void saveWeather(String email, UserWeatherInfo info) {
        info.setEmail(email);
        info.setSearchedAt(LocalDateTime.now());
        repo.save(info);
    }
}
