package com.example.Practice.Fundamentals.services;

import org.springframework.stereotype.Service;

@Service
public class OrderSerice {

    public String getAllOrders() {
        return "Got all orders";
    }
}
