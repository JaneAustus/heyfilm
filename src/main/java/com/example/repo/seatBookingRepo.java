package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.seat_booking_detailes;

@Repository
public interface seatBookingRepo extends JpaRepository<seat_booking_detailes,Integer>{

}
