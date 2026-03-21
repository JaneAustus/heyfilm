package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.SeatBookingDetails;
import com.example.repo.SeatBookingRepository;

@Controller
public class SeatBookingController {

    @Autowired
    private SeatBookingRepository service;

    @GetMapping("/BookingDTLS/save")
    @ResponseBody
    public String saveBooking() {
        SeatBookingDetails details = new SeatBookingDetails();
        details.setName("Sample User");
        details.setDate("22-02-2025");
        details.setEmail("sample@example.com");
        details.setRupees(500);
        details.setTime("10:30");
        details.setMovieName("MERSAL");

        service.save(details);
        return "Booking saved successfully!";
    }
}
