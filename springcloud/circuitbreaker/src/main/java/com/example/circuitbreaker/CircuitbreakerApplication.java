package com.example.circuitbreaker;

import com.example.circuitbreaker.service.OurConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
public class CircuitbreakerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run
                (CircuitbreakerApplication.class, args);
        OurConsumerService ourConsumerService = context.getBean
                (OurConsumerService.class);
        System.out.printf("##############fee is "+ ourConsumerService
                .countArticle());
    }


}
