package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.model.seat_booking_detailes;

@Service
public class importent_Methods {

    // Get seat_booking_detailes for same date, same time, same movie
    public List<seat_booking_detailes> getSameBookings(
            String movie, String date, String time, List<seat_booking_detailes> allBookings) {

        List<seat_booking_detailes> filteredBookings = new ArrayList<>();

        for (seat_booking_detailes booking : allBookings) {

            // Check for null before comparing
            boolean movieMatch = booking.getMovie_Name() != null && booking.getMovie_Name().equalsIgnoreCase(movie);
            boolean dateMatch = booking.getDate() != null && booking.getDate().equalsIgnoreCase(date);
            boolean timeMatch = booking.getTime() != null && booking.getTime().equalsIgnoreCase(time);

            if (movieMatch && dateMatch && timeMatch) {
                filteredBookings.add(booking);
            }
        }

        return filteredBookings;
    }

    public importent_Methods() {
        super();
    }

}

