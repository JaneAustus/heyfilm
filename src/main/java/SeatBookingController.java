import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SeatBookingController {

    // Get available seats
    @GetMapping("/bookSeats")
    public String showSeatBookingPage(Model model) {
        List<Seat> seats = getAvailableSeats();
        model.addAttribute("seats", seats);
        return "Booking";  // The name of the Thymeleaf template
    }

    // Handle booking form submission
    @PostMapping("/bookSeats")
    public String bookSeats(@RequestParam List<String> seats, @RequestParam String passenger, Model model) {
        // Process the booking
        String selectedSeats = String.join(", ", seats);

        // For now, we just show the selected seats on the page
        model.addAttribute("seatsSelected", "Seats Selected: " + selectedSeats);
        return "Booking";  // The name of the Thymeleaf template
    }

    // Generate a list of seats (this can be replaced by a database call)
    private List<Seat> getAvailableSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            boolean isBooked = i % 5 == 0;  // Make every 5th seat booked for example
            seats.add(new Seat("Seat " + i, i, isBooked));
        }
        return seats;
    }

    // Seat class to represent each seat
    public static class Seat {
        private String name;
        private int id;
        private boolean booked;
        private boolean selected;

        public Seat(String name, int id, boolean booked) {
            this.name = name;
            this.id = id;
            this.booked = booked;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public boolean isBooked() {
            return booked;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}
