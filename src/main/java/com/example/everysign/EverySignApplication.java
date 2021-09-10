package com.example.everysign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EverySignApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverySignApplication.class, args);
    }

}
