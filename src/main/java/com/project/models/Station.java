package com.project.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Station")
public class Station implements Serializable {

    @Id
    @Column(name = "station_code")
    private Integer station_code;


    @OneToMany(mappedBy = "station")
    private List<Stop> stops = new ArrayList<Stop>(0);

    public Station() {
    }

    public Station(Integer station_code) {
        this.station_code = station_code;
    }

    public void setStation_code(Integer station_code) {
        this.station_code = station_code;
    }

    public void setStop(List<Stop> stop) {
        this.stops = stop;
    }

    public Integer getStation_code() {
        return station_code;
    }

    public List<Stop> getStop() {

        return stops;
    }
}

