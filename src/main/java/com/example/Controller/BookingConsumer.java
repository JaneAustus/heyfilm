package com.example.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.enums.JmsConstant.JmsConstants;
import com.example.model.BookingMessage;

@Service
public class BookingConsumer {
    
	@Autowired
	private EmailService emailservice;
	
	
	@JmsListener(destination = JmsConstants.BOOKING_QUEUE)
	public void ReceiveMessage(BookingMessage message) {
		
		System.out.println("Message Received from Queue");
		
		emailservice.sendmail(
				message.getEmail(),
				message.getMovieName(),
				message.getSeatNo()
				);
		
	}
}
