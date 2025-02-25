package com.example.Practice.Fundamentals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PracticeFundamentalsApplication {
	public static void main(String[] args) {
		SpringApplication.run(PracticeFundamentalsApplication.class, args);
	}

}
