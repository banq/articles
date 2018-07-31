package com.example.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ModuleApplication {
	private static final Logger logger = LoggerFactory.getLogger(ModuleApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ModuleApplication
				.class, args);
		BookingService bookingService = context.getBean(BookingService.class);
		logger.info(bookingService.toString());
	}
}
