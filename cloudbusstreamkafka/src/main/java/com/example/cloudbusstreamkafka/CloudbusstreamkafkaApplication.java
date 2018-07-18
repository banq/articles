package com.example.cloudbusstreamkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CloudbusstreamkafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudbusstreamkafkaApplication.class, args);
    }
}
