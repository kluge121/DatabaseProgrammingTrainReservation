package com.project.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Train")
public class Train implements Serializable {


    @Id
    @Column(name = "train_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @OneToMany(mappedBy = "train", cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Seat> seats = new ArrayList<Seat>();

    public List<Seat> getSeats() {

        return seats;
    }

    @OneToMany(mappedBy = "train")
    private List<Reserve> reserves = new ArrayList<Reserve>(0);


    @OneToMany(mappedBy = "train")
    private List<Stop> stops = new ArrayList<Stop>(0);


    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int carCount;

    @Column(nullable = false)
    private int seatCount;

    @Column(nullable = false)
    private int startStation;

    @Column(nullable = false)
    private int endStation;

    @Column(nullable = false)
    private int stopInfo;

    @Column(nullable = false)
    private int direction;

    @Column(nullable = false)
    private int starttime;

    public Train() {
    }


    public Train(String type, int carCount, int seatCount, int startStation, int endStation, int stopInfo, int direction, int starttime) {
        this.type = type;
        this.carCount = carCount;
        this.seatCount = seatCount;
        this.startStation = startStation;
        this.endStation = endStation;
        this.stopInfo = stopInfo;
        this.direction = direction;
        this.starttime = starttime;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public void setStartStation(int startStation) {
        this.startStation = startStation;
    }

    public void setEndStation(int endStation) {
        this.endStation = endStation;
    }

    public void setStopInfo(int stopInfo) {
        this.stopInfo = stopInfo;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public Integer getId() {

        return id;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }

    public List<Reserve> getReserves() {

        return reserves;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public String getType() {
        return type;
    }

    public int getCarCount() {
        return carCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public int getStartStation() {
        return startStation;
    }

    public int getEndStation() {
        return endStation;
    }

    public int getStopInfo() {
        return stopInfo;
    }

    public int getDirection() {
        return direction;
    }

    public int getStarttime() {
        return starttime;
    }
}
