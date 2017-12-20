package com.github.alexbabka.planner.client;

import com.github.alexbabka.planner.config.properties.OpenWeatherConnectionProperties;
import com.github.alexbabka.planner.model.WeatherDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenWeatherClient {
    private RestTemplate restTemplate;
    private OpenWeatherConnectionProperties openWeatherConnectionProperties;

    public OpenWeatherClient(RestTemplate restTemplate, OpenWeatherConnectionProperties openWeatherConnectionProperties) {
        this.restTemplate = restTemplate;
        this.openWeatherConnectionProperties = openWeatherConnectionProperties;
    }

    public WeatherDetails getCurrentWeather() {
        String apiUrl = openWeatherConnectionProperties.getApiUrl();

        UriComponents url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("id", "2759794")
                .queryParam("units", "metric")
                .queryParam("APPID", openWeatherConnectionProperties.getApiKey())
                .build();

        return restTemplate.getForObject(url.toUri(), WeatherDetails.class);
    }
}
