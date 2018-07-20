package com.example.configserverjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigserverJdbcApplication.class, args);
    }
}
