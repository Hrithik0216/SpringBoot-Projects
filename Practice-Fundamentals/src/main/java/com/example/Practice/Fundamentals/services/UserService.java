package com.example.Practice.Fundamentals.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    public String getAllUsers() {
        return "Go allusers";
    }

    public void someMethod() {
        LOGGER.info("Go someMethod");
        System.out.println("Some method");
    }
}
