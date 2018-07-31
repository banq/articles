package com.example.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BookingModuleConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(BookingModuleConfiguration.class);

	@PostConstruct
	public void postConstruct(){
		logger.info("BOOKING MODULE LOADED!");
	}

	@Bean
	public BookingService bookingService(){
		return new BookingService();
	}
}
