package com.example.consumer.discoveryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ConsumerDiscoveryClientApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext ctx  = SpringApplication.run
                (ConsumerDiscoveryClientApplication.class, args);
        ConsumerDiscoveryClient consumerDiscoveryClient = ctx.getBean
                (ConsumerDiscoveryClient.class);
        System.out.printf("final result=" + consumerDiscoveryClient
                .getProducerMethod());
    }

}
