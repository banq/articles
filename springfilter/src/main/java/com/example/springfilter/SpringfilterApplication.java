package com.example.springfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringfilterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringfilterApplication.class, args);
	}
}
