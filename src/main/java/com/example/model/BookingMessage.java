package com.example.model;

import java.io.Serializable;

public class BookingMessage implements Serializable{

	   private static final long serialVersionUID = 1L;
	
	private String email;
	private String MovieName;
	private int seatNo;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String movieName) {
		MovieName = movieName;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	
	
	
	
}
