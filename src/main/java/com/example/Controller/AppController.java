package com.example.Controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.SeatBookingDetails;
import com.example.model.BookingMessage;
import com.example.model.User;
import com.example.repo.UserRepository;
import com.example.repo.SeatBookingRepository;

@Controller
public class AppController {

    public static class Movie {
        private String title;
        private String image;
        private double rating;
        private String genre;

        public Movie(String title, String image, double rating, String genre) {
            this.title = title;
            this.image = image;
            this.rating = rating;
            this.genre = genre;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public double getRating() {
            return rating;
        }

        public String getGenre() {
            return genre;
        }
    }

    private List<Movie> availableMovies = Arrays.asList(
            new Movie("MERSAL", "merasal.jpg", 5.0, "Action, Drama"),
            new Movie("LEGENT", "legent.jpg", 3.0, "Horror, Mystery"),
            new Movie("NAAISEKER", "naaisekar.jpg", 4.0, "Comedy, Drama"));

    @GetMapping("/search")
    public String searchMovies(@RequestParam String query, Model model) {
        List<Movie> results = availableMovies.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        m.getGenre().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "searchResults";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingProducer bookingProducer;

    @Autowired
    private SeatBookingRepository seatBookingRepository;

    private String movieName;
    private String time;
    private String date;
    private String bookingUserName;
    private String bookingUserEmail;
    private BigInteger bookingUserMobile;

    @GetMapping("/Register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "registerform";
    }

    @PostMapping("/Register")
    public String registerUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                return "home";
            }
        }
        return "FailLogin";
    }

    @GetMapping("/privacy")
    public String privacyPolicy() {
        return "privacy";
    }

    @GetMapping("/terms")
    public String termsAndConditions() {
        return "TermsAndConditions";
    }

    @GetMapping("/FAQ")
    public String FAQ() {
        return "FAQ";
    }

    @GetMapping("/movies")
    public String movies() {
        return "movies";
    }

    @GetMapping("/showTime")
    public String showTimes() {
        return "showTiming";
    }

    @GetMapping("/Bookings")
    public String bookingHistory(Model model) {
        List<SeatBookingDetails> allBookings = seatBookingRepository.findAll();
        model.addAttribute("AllBooking", allBookings);
        return "BookingHistory";
    }

    @GetMapping("/contactUs")
    public String contactUs() {
        return "contactUs";
    }

    @GetMapping("/BookNow")
    public String bookNow() {
        return "booking";
    }

    @GetMapping("/ForgotPass")
    public String ForgotPass() {
        return "ForgotPassword";
    }

    @GetMapping("/movie-details")
    public String movieInfo(@RequestParam String movie) {
        switch (movie.toLowerCase()) {
            case "legent":
                return "legentDTLS";
            case "naaisekar":
                return "naaisekarDTLS";
            case "merasal":
                return "merasalDTLS";
            default:
                return "movies";
        }
    }

    @GetMapping("/BookingDTLS")
    public String bookingDetails(
            @RequestParam String movie,
            @RequestParam String showtime,
            @RequestParam String showDate,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            Model model) {

        movieName = movie;
        time = showtime;
        date = showDate;
        bookingUserName = name;
        bookingUserEmail = email;
        bookingUserMobile = new BigInteger(phone);

        List<SeatBookingDetails> allBookings = seatBookingRepository.findAll();
        importent_Methods sa = new importent_Methods(); // Still using the utility for now, but fixed logic later
        List<SeatBookingDetails> bookedSeats = sa.getSameBookings(movieName, date, time, allBookings);

        if (bookedSeats != null && !bookedSeats.isEmpty()) {
            String bookedSeatsStr = bookedSeats.stream()
                    .map(SeatBookingDetails::getSeats)
                    .filter(s -> s != null)
                    .collect(Collectors.joining(","));
            List<String> bookedSeatList = Arrays.asList(bookedSeatsStr.split(","));
            model.addAttribute("UnAvailSeats", bookedSeatList);
        }

        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("movieName", movieName);

        return "seatselection";
    }

    @PostMapping("/confirm-booking")
    public String selectedSeats(@RequestParam String seat, Model model) {
        int pricePerSeat = 150;
        int totalPrice = seat.split(",").length * pricePerSeat;

        SeatBookingDetails booking = new SeatBookingDetails();
        booking.setSeats(seat);
        booking.setDate(date);
        booking.setEmail(bookingUserEmail);
        booking.setName(bookingUserName);
        booking.setMovieName(movieName);
        booking.setTime(time);
        booking.setMobile(bookingUserMobile);
        booking.setRupees(totalPrice);

        seatBookingRepository.save(booking);

        BookingMessage msg = new BookingMessage();
        msg.setEmail(bookingUserEmail);
        msg.setMovieName(movieName);
        msg.setSeatNo(seat.split(",").length);

        bookingProducer.sendBookingMessage(msg);

        model.addAttribute("movieName", movieName);
        model.addAttribute("seats", seat);
        model.addAttribute("time", time);
        model.addAttribute("price", totalPrice);

        return "BookingSuccess";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
