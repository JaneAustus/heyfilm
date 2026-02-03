package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@EntityScan(basePackages = { "com.example.model" })
@ComponentScan(basePackages = { "com.example.Controller", "com.example.repo", "com.example.model" })
@SpringBootApplication
@EnableJms
public class MovieTicketBookingFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketBookingFinalApplication.class, args);
	}

}
