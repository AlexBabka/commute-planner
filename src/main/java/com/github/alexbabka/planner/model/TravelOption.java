package com.github.alexbabka.planner.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class TravelOption {
    @XmlElement(name = "ActueleVertrekTijd")
    private String departureTime;
    @XmlElement(name = "ActueleAankomstTijd")
    private String arrivalTime;
    @XmlElement(name = "ActueleReisTijd")
    private String duration;
    @XmlElement(name = "AantalOverstappen")
    private int changes;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getChanges() {
        return changes;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    @XmlRootElement(name = "ReisMogelijkheden")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class TravelOptions {
        @XmlElement(name = "ReisMogelijkheid")
        private List<TravelOption> options;

        public List<TravelOption> getOptions() {
            return options;
        }
    }
}
