package com.github.alexbabka.planner.service;

import com.github.alexbabka.planner.client.train.TrainInformationClient;
import com.github.alexbabka.planner.model.TrainStation;
import com.github.alexbabka.planner.model.TravelOption;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private TrainInformationClient trainInformationClient;

    public TrainService(TrainInformationClient trainInformationClient) {
        this.trainInformationClient = trainInformationClient;
    }

    public List<TrainStation> findAllStations() {
        return trainInformationClient.retrieveAllTrainStations();
    }

    public List<TravelOption> getTravelAdvice(String fromStation, String toStation) {
        return trainInformationClient.retrieveTravelAdvice(fromStation, toStation);
    }
}
