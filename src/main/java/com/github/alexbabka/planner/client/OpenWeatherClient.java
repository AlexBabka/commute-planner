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

    public WeatherDetails getCurrentWeather(String cityId) {
        String apiUrl = openWeatherConnectionProperties.getUrl();
        String apiKey = openWeatherConnectionProperties.getSecretKey();

        // TODO make configurable
        String measurementUnit = "metric";

        UriComponents url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("id", cityId)
                .queryParam("units", measurementUnit)
                .queryParam("APPID", apiKey)
                .build();

        return restTemplate.getForObject(url.toUri(), WeatherDetails.class);
    }
}
