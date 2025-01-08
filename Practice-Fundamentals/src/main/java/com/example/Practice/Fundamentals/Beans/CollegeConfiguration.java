package com.example.Practice.Fundamentals.Beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollegeConfiguration {
    @Bean
    public College getCollegeBean(){
        return new College();
    }
}
