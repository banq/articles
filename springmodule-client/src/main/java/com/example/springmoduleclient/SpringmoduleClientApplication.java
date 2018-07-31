package com.example.springmoduleclient;

import com.example.module.EnableBookingModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBookingModule
public class SpringmoduleClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringmoduleClientApplication.class, args);
	}
}
