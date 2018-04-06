package com.project.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Date")
public class Date implements Serializable {

    @Id
    private
    int date_id;

    @OneToMany(mappedBy = "date")
    private Set<ReserveSeat> reserveSeats = new HashSet<ReserveSeat>(0);

    public Date() {
    }

    public Date(int date_id) {
        this.date_id = date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
    }

    public void setReserveSeats(Set<ReserveSeat> reserveSeats) {
        this.reserveSeats = reserveSeats;
    }

    public int getDate_id() {

        return date_id;
    }

    public Set<ReserveSeat> getReserveSeats() {
        return reserveSeats;
    }
}
