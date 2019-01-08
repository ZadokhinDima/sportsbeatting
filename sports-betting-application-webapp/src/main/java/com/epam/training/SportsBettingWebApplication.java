package com.epam.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SportsBettingWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsBettingWebApplication.class, args);
    }

}

