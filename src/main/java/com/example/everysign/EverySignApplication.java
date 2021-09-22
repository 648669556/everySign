package com.example.everysign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.everysign.core.mapper")
public class EverySignApplication {
    public static void main(String[] args) {
        SpringApplication.run(EverySignApplication.class, args);
    }

}
