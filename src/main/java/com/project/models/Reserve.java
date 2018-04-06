package com.project.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Reserve")
public class Reserve implements Serializable {


    @Id
    @GeneratedValue
    @Column
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private
    String train_type;

    @Column(nullable = false)
    private
    int car_num;

    @Column(nullable = false)
    private
    int seat_num;

    @Column(nullable = false)
    private
    int start_date;

    @Column(nullable = false)
    private
    String start_station;

    @Column(nullable = false)
    private
    String end_station;

    @Column(nullable = false)
    private
    String startTime;

    @Column(nullable = false)
    private
    String endTime;


    public Reserve(String train_type, int car_num, int seat_num, int start_date) {
        this.train_type = train_type;
        this.car_num = car_num;
        this.seat_num = seat_num;
        this.start_date = start_date;
        this.start_station = start_station;
        this.end_station = end_station;

    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Reserve() {
    }

    public int getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }


    public User getUser() {
        return user;
    }

    public String getTrain_type() {
        return train_type;
    }

    public int getCar_num() {
        return car_num;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public int getStart_date() {
        return start_date;
    }

    public String getStart_station() {
        return start_station;
    }

    public String getEnd_station() {
        return end_station;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    public void setCar_num(int car_num) {
        this.car_num = car_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public void setStart_date(int start_date) {
        this.start_date = start_date;
    }

    public void setStart_station(String start_station) {
        this.start_station = start_station;
    }

    public void setEnd_station(String end_station) {
        this.end_station = end_station;
    }
}
