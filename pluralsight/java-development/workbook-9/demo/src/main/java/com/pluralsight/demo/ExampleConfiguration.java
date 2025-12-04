package com.pluralsight.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfiguration {

    @Bean
    public String nameBean() {
        return "Ray";
    }
}
