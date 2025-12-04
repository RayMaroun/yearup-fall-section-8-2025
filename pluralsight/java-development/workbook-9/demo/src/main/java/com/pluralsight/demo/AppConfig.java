package com.pluralsight.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Product hotChocolate() {
        return new Product(55, "Hot Chocolate", "Beverages", 2.50);
    }

    @Bean
    public Product coke() {
        return new Product(56, "Mountain Dew", "Beverages", 1.50);
    }
}
