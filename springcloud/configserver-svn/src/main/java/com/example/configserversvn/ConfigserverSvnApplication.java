package com.example.configserversvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverSvnApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigserverSvnApplication.class, args);
    }
}
