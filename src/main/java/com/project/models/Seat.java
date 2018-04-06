package com.project.models;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Seat")
public class Seat implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private
    int seat_id;


    @Column(nullable = false)
    private int car_num;
    @Column(nullable = false)
    private int seat_num;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "train_id")
    private
    Train train;


    @OneToMany(mappedBy = "seat")
    private Set<ReserveSeat> reserveSeats = new HashSet<ReserveSeat>(0);

    public Seat(int car_num, int seat_num, Train train) {
        this.car_num = car_num;
        this.seat_num = seat_num;
        this.train = train;
    }

    public Seat() {
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public void setCar_num(int car_num) {
        this.car_num = car_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setReserveSeats(Set<ReserveSeat> reserveSeats) {
        this.reserveSeats = reserveSeats;
    }

    public int getSeat_id() {

        return seat_id;
    }

    public int getCar_num() {
        return car_num;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public Train getTrain() {
        return train;
    }

    public Set<ReserveSeat> getReserveSeats() {
        return reserveSeats;
    }
}
