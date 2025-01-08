package com.example.Practice.Fundamentals.controllers;

import com.example.Practice.Fundamentals.services.OrderSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderSerice orderSerice;
    @GetMapping("/getAllOrders")
    public String getAllOrders(){
        return orderSerice.getAllOrders();
    }
}
