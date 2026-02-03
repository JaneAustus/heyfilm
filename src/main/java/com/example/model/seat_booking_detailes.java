package com.example.model;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
@Component
@Scope("prototype")
public class seat_booking_detailes implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String Seatts;
	private int Rupees;
	private BigInteger mobile;
	private String time;
	private String Date;
	private String Movie_Name;
	public seat_booking_detailes(int id, String name, String email, String seatts, int rupees, BigInteger mobile,
			String time, String date, String movie_Name) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		Seatts = seatts;
		Rupees = rupees;
		this.mobile = mobile;
		this.time = time;
		Date = date;
		Movie_Name = movie_Name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getSeatts() {
		return Seatts;
	}
	public void setSeatts(String selectedSeats) {
		Seatts = selectedSeats;
	}
	public int getRupees() {
		return Rupees;
	}
	public seat_booking_detailes() {
		
	}
	public void setRupees(int rupees) {
		Rupees = rupees;
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
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getMovie_Name() {
		return Movie_Name;
	}
	public void setMovie_Name(String movie_Name) {
		Movie_Name = movie_Name;
	}
	
	
	
	
	
	
	
	
	

}
