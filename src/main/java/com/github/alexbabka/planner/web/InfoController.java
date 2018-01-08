package com.github.alexbabka.planner.web;

import com.github.alexbabka.planner.model.TrainStation;
import com.github.alexbabka.planner.model.TravelOption;
import com.github.alexbabka.planner.model.WeatherDetails;
import com.github.alexbabka.planner.service.TrainService;
import com.github.alexbabka.planner.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class InfoController {
    @Autowired
    private WeatherService weatherService;

    @Autowired
    private TrainService trainService;

    @GetMapping("weather")
    public WeatherDetails weather() {
        return weatherService.getCurrentWeather();
    }

    @GetMapping("train-stations")
    public List<TrainStation> stations() {
        return trainService.findAllStations();
    }

    @GetMapping("travel-planner")
    public List<TravelOption> travelOptions(@RequestParam String fromStation, @RequestParam String toStation) {
        Assert.notNull(fromStation, "fromStation is required");
        Assert.notNull(toStation, "toStation is required");

        return trainService.getTravelAdvice(fromStation, toStation);
    }
}
