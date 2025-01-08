package com.example.Practice.Fundamentals;

import com.example.Practice.Fundamentals.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UserServiceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(UserServiceApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        UserService userService = context.getBean(UserService.class);
        userService.someMethod();
    }
}
