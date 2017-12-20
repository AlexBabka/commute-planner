package com.github.alexbabka.planner.service;

import com.github.alexbabka.planner.client.OpenWeatherClient;
import com.github.alexbabka.planner.model.WeatherDetails;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private OpenWeatherClient openWeatherClient;

    public WeatherService(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    public WeatherDetails getCurrentWeather() {
        return openWeatherClient.getCurrentWeather();
    }
}
