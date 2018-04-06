package com.project.models;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ReserveSeat")
public class ReserveSeat implements Serializable {


    @Id
    @GeneratedValue
    @Column
    private int id;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "date_id")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "seat_id")
    private Seat seat;


    @Column(nullable = false)
    private
    int train_num;

    @Column(nullable = false)
    private
    int car_num;

    @Column(nullable = false)
    private
    int seat_num;

    @Column(nullable = false)
    private
    int seat_info;

    public ReserveSeat() {
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }


    public void setCar_num(int car_num) {
        this.car_num = car_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public void setSeat_info(int seat_info) {
        this.seat_info = seat_info;
    }

    public int getId() {

        return id;
    }

    public Date getDate() {
        return date;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setTrain_num(int train_num) {
        this.train_num = train_num;
    }

    public int getTrain_num() {

        return train_num;
    }

    public int getCar_num() {
        return car_num;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public int getSeat_info() {
        return seat_info;
    }

    public ReserveSeat(int train_num, int car_num, int seat_num, int seat_info) {
        this.train_num = train_num;
        this.car_num = car_num;
        this.seat_num = seat_num;
        this.seat_info = seat_info;
    }
}
