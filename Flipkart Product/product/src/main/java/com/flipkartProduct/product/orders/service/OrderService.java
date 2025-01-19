package com.flipkartProduct.product.orders.service;

import com.flipkartProduct.product.Enumeration.OrderStatus;
import com.flipkartProduct.product.orders.model.OrderModel;
import com.flipkartProduct.product.orders.model.OrderRequestBody;
import com.flipkartProduct.product.orders.respository.OrderRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class OrderService implements OrderServiceInterface {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    @Autowired
    OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> placeOrder(OrderRequestBody order, String userId) {
        if(order==null || order.getCartId()=="" || order.getTotalAmount()==0 || userId==""){
            LOGGER.info("The order details are empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The order details are empty");
        }
        OrderModel orderModel = new OrderModel(
                userId,
                order.getCartId(),
                new Date(),
                OrderStatus.CONFIRMED,
                order.getTotalAmount());
        orderRepository.save(orderModel);
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(orderModel);
    }
}
