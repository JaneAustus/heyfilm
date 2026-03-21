package com.example.model;

import java.io.Serializable;
import java.math.BigInteger;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "bookings")
@Component
@Scope("prototype")
public class SeatBookingDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String seats;
    private int rupees;
    private BigInteger mobile;
    private String time;
    private String date;
    private String movieName;

    public SeatBookingDetails() {
    }

    public SeatBookingDetails(String id, String name, String email, String seats, int rupees, BigInteger mobile,
            String time, String date, String movieName) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.seats = seats;
        this.rupees = rupees;
        this.mobile = mobile;
        this.time = time;
        this.date = date;
        this.movieName = movieName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public int getRupees() {
        return rupees;
    }

    public void setRupees(int rupees) {
        this.rupees = rupees;
    }

    public BigInteger getMobile() {
        return mobile;
    }

    public void setMobile(BigInteger mobile) {
        this.mobile = mobile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
