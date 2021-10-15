package com.engine.realestatesearchapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class RealEstateSearchAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealEstateSearchAppApplication.class, args);
    }

}
