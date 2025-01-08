package com.example.Practice.Fundamentals;


import com.example.Practice.Fundamentals.services.OrderSerice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OrderserviceApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(OrderserviceApplication.class).web(WebApplicationType.NONE).run(args);
        OrderSerice process= context.getBean(OrderSerice.class);

    }
}
