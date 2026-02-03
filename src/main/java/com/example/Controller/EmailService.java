package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
	@Autowired
	private JavaMailSender Emailsender;
	
	public void sendmail (String to , String Movie , int seats) {
		
		SimpleMailMessage sms = new SimpleMailMessage();
		
		sms.setTo(to);
		sms.setSubject("Ticket Booking Confirmed");
		sms.setText("Your ticket has been booked successfully!\n\n" +
	            "Movie: " + Movie + "\n" +
	            "Number of Seats: " + seats + "\n\n" +
	            "Enjoy your show 😊");
		Emailsender.send(sms);
		
	};
	
}
