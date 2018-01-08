package com.github.alexbabka.planner.client.train;

public enum TrainApi {
    STATIONS("ns-api-stations-v2"),
    TRAVEL_ADVICE("ns-api-treinplanner");

    private String path;

    TrainApi(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
