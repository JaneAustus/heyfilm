package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.seat_booking_detailes;
import com.example.repo.seatBookingRepo;

@Controller
public class SeatBookingController {

    @Autowired
    private seatBookingRepo service;

    // Endpoint to save a sample booking
    @GetMapping("/BookingDTLS/save")
    @ResponseBody
    public String saveBooking() {

        // Create a new seat booking details object
        seat_booking_detailes details = new seat_booking_detailes();
        details.setName("Masila");
        details.setDate("22-02-2001");
        details.setEmail("Masila@Gmail.com");
        details.setId(4);
        details.setRupees(50000);
        details.setTime("10:30");

        // Save to database
        service.save(details);

        return "Booking saved successfully!";
    }
}


