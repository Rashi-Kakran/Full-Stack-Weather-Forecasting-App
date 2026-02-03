package com.wApp.WeatherForcasting.repository;

import com.wApp.WeatherForcasting.model.UserWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWeatherInfoRepo extends JpaRepository<UserWeatherInfo, Long> {
    //List<UserWeatherInfo> findByEmail(String email);

}
