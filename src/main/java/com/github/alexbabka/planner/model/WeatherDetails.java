package com.github.alexbabka.planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class WeatherDetails {
    private Main main;
    private Wind wind;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    private static class Main {
        @JsonProperty("temp")
        private BigDecimal temperature;

        public BigDecimal getTemperature() {
            return temperature;
        }

        public void setTemperature(BigDecimal temperature) {
            this.temperature = temperature;
        }
    }

    private static class Wind {
        @JsonProperty("speed")
        private BigDecimal windSpeed;

        public BigDecimal getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(BigDecimal windSpeed) {
            this.windSpeed = windSpeed;
        }
    }
}
