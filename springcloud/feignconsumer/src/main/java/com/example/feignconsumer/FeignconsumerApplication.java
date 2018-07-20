package com.example.feignconsumer;

import com.example.feignconsumer.service.ConsumerService;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.naming.Context;
import java.io.IOException;

@SpringBootApplication
@EnableFeignClients
public class FeignconsumerApplication {

    public static void main(String[] args)  {
        ApplicationContext context = SpringApplication.run(FeignconsumerApplication
                        .class, args);
        ConsumerService consumerService = context.getBean(ConsumerService
                .class);
        System.out.printf("#############all articles ok" + consumerService
                .getAllArticles());
    }


}
