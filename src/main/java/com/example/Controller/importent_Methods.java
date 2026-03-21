package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.model.SeatBookingDetails;

@Service
public class importent_Methods {

    public List<SeatBookingDetails> getSameBookings(
            String movie, String date, String time, List<SeatBookingDetails> allBookings) {

        List<SeatBookingDetails> filteredBookings = new ArrayList<>();

        for (SeatBookingDetails booking : allBookings) {
            boolean movieMatch = booking.getMovieName() != null && booking.getMovieName().equalsIgnoreCase(movie);
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
