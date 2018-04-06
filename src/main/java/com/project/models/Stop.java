package com.project.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Stop")
public class Stop implements Serializable {


    @Id
    @GeneratedValue
    @Column
    private int id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    private Train train;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(nullable = false)
    private
    int start_time;

    @Column(nullable = false)
    private
    int direction;


    public void setId(int id) {
        this.id = id;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getId() {

        return id;
    }

    public Train getTrain() {
        return train;
    }

    public Station getStation() {
        return station;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {

        return direction;
    }


    public String timeToStirng() {

        StringBuffer sb = new StringBuffer();

        if ((start_time / 100) < 10) {
            sb
                    .append("0").
                    append(start_time / 100)
                    .append(":");
        } else {
            sb.append(start_time / 100)
                    .append(":");
        }

        if ((start_time % 100) < 10) {
            sb
                    .append("0").
                    append(start_time % 100);
        } else {
            sb.append(start_time % 100);
        }


        return sb.toString();
    }
}
