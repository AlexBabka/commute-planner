package com.github.alexbabka.planner.client;

import com.github.alexbabka.planner.config.properties.TrainApiConnectionProperties;
import com.github.alexbabka.planner.model.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + createBasicAuthHeader());
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<TrainStation.Stations> responseEntity
                = restTemplate.exchange(trainApiConnectionProperties.getUrl(), HttpMethod.GET, httpEntity, TrainStation.Stations.class);

        return Optional.ofNullable(responseEntity.getBody())
                .map(TrainStation.Stations::getTrainStations)
                .orElse(Collections.emptyList());
    }

    private String createBasicAuthHeader() {
        String plainCreds = trainApiConnectionProperties.getUsername() + ":" + trainApiConnectionProperties.getPassword();
        return Base64.getEncoder().encodeToString(plainCreds.getBytes());
    }
}
