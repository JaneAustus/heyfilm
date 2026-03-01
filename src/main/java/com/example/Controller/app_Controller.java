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

import com.example.model.seat_booking_detailes;
import com.example.model.BookingMessage;
import com.example.model.User;
import com.example.repo.UserInterFace;
import com.example.repo.seatBookingRepo;

import jakarta.transaction.Transactional;

@Controller
public class app_Controller {

    // Simple Movie class for search
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
    private UserInterFace userService;

    @Autowired
    private BookingProducer bookingproducer;

    @Autowired
    private seatBookingRepo seatService;

    // Temporary booking info (can be improved with session)
    private String movieName;
    private String time;
    private String date;
    private String bookingUserName;
    private String bookingUserEmail;
    private BigInteger bookingUserMobile;

    // ================== Registration ==================
    @GetMapping("/Register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "registerform"; // Thymeleaf template
    }

    @PostMapping("/Register")
    public String registerUser(@ModelAttribute User user) {
        userService.save(user);
        return "login";
    }

    // ================== Login ==================
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {

        List<User> users = userService.findAll();
        for (User user : users) {
            if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                return "home";
            }
        }
        return "FailLogin";
    }

    // ================== Static Pages ==================
    @GetMapping("/privecy")
    public String privacyPolicy() {
        return "Privecy";
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
        List<seat_booking_detailes> allBookings = seatService.findAll();
        model.addAttribute("AllBooking", allBookings);
        return "BookingHistory";
    }

    @GetMapping("/contectUs")
    public String contactUs() {
        return "contectUs";
    }

    @GetMapping("/BookNow")
    public String bookNow() {
        return "booking";
    }

    @GetMapping("/ForgotPass")
    public String ForgotPass() {
        return "ForgotPassword";
    }

    // ================== Movie Details ==================
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
                return "movies"; // fallback
        }
    }

    // ================== Booking Seat Selection ==================
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

        List<seat_booking_detailes> allBookings = seatService.findAll();
        importent_Methods sa = new importent_Methods();
        List<seat_booking_detailes> bookedSeats = sa.getSameBookings(movieName, date, time, allBookings);

        if (bookedSeats != null && !bookedSeats.isEmpty()) {
            String[] arr = new String[50];
            int a = 0;
            for (seat_booking_detailes item : bookedSeats) {
                arr[a++] = item.getSeatts();
            }
            String bookedSeatsStr = Arrays.stream(arr).filter(s -> s != null).collect(Collectors.joining(","));
            List<String> bookedSeatList = Arrays.asList(bookedSeatsStr.split(","));
            model.addAttribute("UnAvailSeats", bookedSeatList);
        }

        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("movieName", movieName);

        return "seatselection";
    }

    // ================== Confirm Seat Booking ==================
    @PostMapping("/Masila")
    @Transactional
    public String selectedSeats(@RequestParam String seat, Model model) {

        int pricePerSeat = 150;
        int totalPrice = seat.split(",").length * pricePerSeat;

        seat_booking_detailes booking = new seat_booking_detailes();
        booking.setSeatts(seat);
        booking.setDate(date);
        booking.setEmail(bookingUserEmail);
        booking.setName(bookingUserName);
        booking.setMovie_Name(movieName);
        booking.setTime(time);
        booking.setMobile(bookingUserMobile);
        booking.setRupees(totalPrice);

        seatService.save(booking);

        BookingMessage msg = new BookingMessage();
        msg.setEmail(bookingUserEmail);
        msg.setMovieName(movieName);
        msg.setSeatNo(seat.split(",").length);

        bookingproducer.sendBookingMessage(msg);

        model.addAttribute("movieName", movieName);
        model.addAttribute("seats", seat);
        model.addAttribute("time", time);
        model.addAttribute("price", totalPrice);

        return "BookingSuccess";
    }

    // ================== Home ==================
    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
