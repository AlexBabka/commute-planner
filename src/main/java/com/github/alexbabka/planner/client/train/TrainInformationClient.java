package com.github.alexbabka.planner.client.train;

import com.github.alexbabka.planner.config.properties.TrainApiConnectionProperties;
import com.github.alexbabka.planner.model.TrainStation;
import com.github.alexbabka.planner.model.TravelOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TrainInformationClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TrainApiConnectionProperties trainApiConnectionProperties;

    public List<TrainStation> retrieveAllTrainStations() {
        HttpEntity httpEntity = createHttpEntityWithBasicAuthHeaders();

        String baseUrl = trainApiConnectionProperties.getUrl();

        String serviceUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(TrainApi.STATIONS.getPath())
                .build()
                .toString();

        ResponseEntity<TrainStation.Stations> responseEntity
                = restTemplate.exchange(serviceUrl, HttpMethod.GET, httpEntity, TrainStation.Stations.class);

        return Optional.ofNullable(responseEntity.getBody())
                .map(TrainStation.Stations::getTrainStations)
                .orElse(Collections.emptyList());
    }

    public List<TravelOption> retrieveTravelAdvice(String fromStation, String toStation) {
        HttpEntity httpEntity = createHttpEntityWithBasicAuthHeaders();

        String baseUrl = trainApiConnectionProperties.getUrl();

        String serviceUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(TrainApi.TRAVEL_ADVICE.getPath())
                .queryParam("fromStation", fromStation)
                .queryParam("toStation", toStation)
                .build()
                .toString();

        ResponseEntity<TravelOption.TravelOptions> responseEntity
                = restTemplate.exchange(serviceUrl, HttpMethod.GET, httpEntity, TravelOption.TravelOptions.class);

        return Optional.ofNullable(responseEntity.getBody())
                .map(TravelOption.TravelOptions::getOptions)
                .orElse(Collections.emptyList());
    }

    private HttpEntity createHttpEntityWithBasicAuthHeaders() {
        String plainCreds = trainApiConnectionProperties.getUsername() + ":" + trainApiConnectionProperties.getPassword();
        String encodedCredentials = Base64.getEncoder().encodeToString(plainCreds.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);

        return new HttpEntity<String>(headers);
    }
}
