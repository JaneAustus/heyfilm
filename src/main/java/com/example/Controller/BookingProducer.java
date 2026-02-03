package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.enums.JmsConstant.JmsConstants;
import com.example.model.BookingMessage;

@Service
public class BookingProducer {
 
	@Autowired
	private JmsTemplate jmstemplete;
	
	public void sendBookingMessage(BookingMessage message) {
		jmstemplete.convertAndSend(JmsConstants.BOOKING_QUEUE, message);
	}
	
}
