package com.github.alexbabka.planner.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class TrainStation {
    @XmlElement(name = "Code")
    private String code;
    @XmlElement(name = "Namen")
    private StationName name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public StationName getName() {
        return name;
    }

    public void setName(StationName name) {
        this.name = name;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    private static class StationName {
        @XmlElement(name = "Kort")
        private String shortName;
        @XmlElement(name = "Middel")
        private String mediumName;
        @XmlElement(name = "Lang")
        private String longName;

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getMediumName() {
            return mediumName;
        }

        public void setMediumName(String mediumName) {
            this.mediumName = mediumName;
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }
    }

    @XmlRootElement(name = "Stations")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Stations {
        @XmlElement(name = "Station")
        private List<TrainStation> trainStations;

        public List<TrainStation> getTrainStations() {
            return trainStations;
        }
    }
}
