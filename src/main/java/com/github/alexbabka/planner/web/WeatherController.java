package com.github.alexbabka.planner.web;

import com.github.alexbabka.planner.model.WeatherDetails;
import com.github.alexbabka.planner.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("weather")
    public WeatherDetails weather() {
        return weatherService.getCurrentWeather();
    }
}
