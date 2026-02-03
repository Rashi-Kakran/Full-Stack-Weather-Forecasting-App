package com.wApp.WeatherForcasting.service;

import com.wApp.WeatherForcasting.model.UserWeatherInfo;
import com.wApp.WeatherForcasting.repository.UserWeatherInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String API_URL =
            "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    @Autowired
    private UserWeatherInfoRepo userWeatherInfoRepo;

    public Map<String, Object> getWeatherAndSave(String city, String email) {

        try {
            RestTemplate restTemplate = new RestTemplate();

            // Encode city name (important for spaces like "New Delhi")
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String url = String.format(API_URL, encodedCity, apiKey);

            Map<String, Object> response =
                    restTemplate.getForObject(url, Map.class);

            // Safety check
            if (response == null || response.get("main") == null) {
                throw new RuntimeException("City not found");
            }

            Map<String, Object> main = (Map<String, Object>) response.get("main");
            Map<String, Object> wind = (Map<String, Object>) response.get("wind");
            List<Map<String, Object>> weatherList =
                    (List<Map<String, Object>>) response.get("weather");

            Map<String, Object> weatherObj = weatherList.get(0);

            // Response for frontend
            Map<String, Object> result = new HashMap<>();
            result.put("city", city);
            result.put("temp", main.get("temp"));
            result.put("tempMin", main.get("temp_min"));
            result.put("tempMax", main.get("temp_max"));
            result.put("humidity", main.get("humidity"));
            result.put("feelsLike", main.get("feels_like"));
            result.put("weather", weatherObj.get("description"));
            result.put("windSpeed", wind.get("speed"));
            result.put("isRaining", "Rain".equals(weatherObj.get("main")));

            // Save to DB
            UserWeatherInfo info = new UserWeatherInfo();
            info.setEmail(email);
            info.setCity(city);
            info.setTemp(((Number) main.get("temp")).doubleValue());
            info.setTempMin(((Number) main.get("temp_min")).doubleValue());
            info.setTempMax(((Number) main.get("temp_max")).doubleValue());
            info.setHumidity(((Number) main.get("humidity")).intValue());
            info.setFeelsLike(((Number) main.get("feels_like")).doubleValue());
            info.setWeather((String) weatherObj.get("description"));
            info.setWindSpeed(((Number) wind.get("speed")).doubleValue());
            info.setIsRaining("Rain".equals(weatherObj.get("main")));
            info.setSearchedAt(LocalDateTime.now());

            userWeatherInfoRepo.save(info);

            return result;

        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("City not found");
        } catch (Exception e) {
            throw new RuntimeException("Unable to fetch weather data");
        }
    }
}
