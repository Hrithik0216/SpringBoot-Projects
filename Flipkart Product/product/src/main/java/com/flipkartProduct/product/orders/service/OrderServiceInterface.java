package com.flipkartProduct.product.orders.service;

import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.orders.model.OrderModel;
import com.flipkartProduct.product.orders.model.OrderRequestBody;
import org.springframework.http.ResponseEntity;

public interface OrderServiceInterface {
    ResponseEntity<?> placeOrder(OrderRequestBody order);
}
