package com.flipkartProduct.product.orders.service;

import com.flipkartProduct.product.Enumeration.OrderStatus;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.orders.model.OrderModel;
import com.flipkartProduct.product.orders.model.OrderRequestBody;
import com.flipkartProduct.product.orders.respository.OrderRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService implements OrderServiceInterface {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> placeOrder(OrderRequestBody order) {
        OrderModel orderModel = new OrderModel(
                order.getUserId(),
                order.getCartId(),
                new Date(),
                OrderStatus.CONFIRMED,
                order.getTotalAmount());

        orderRepository.save(orderModel);
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(orderModel);
    }
}
