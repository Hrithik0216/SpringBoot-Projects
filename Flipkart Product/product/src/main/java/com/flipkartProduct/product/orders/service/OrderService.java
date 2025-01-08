package com.flipkartProduct.product.orders.service;

import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.orders.model.OrderModel;
import com.flipkartProduct.product.orders.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService implements OrderServiceInterface{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public ResponseEntity<String> placeOrder(Cart cart) {
        OrderModel order = new OrderModel();
        AtomicInteger total= new AtomicInteger();
        order.setCartId(cart.getId());
        order.setOrderDate(new Date());
        order.setOrderStatus("waiting");
        order.setCart(cart.getProductList());
        order.setUserId(cart.getUser());
        cart.getProductList().forEach(product->{
            total.addAndGet(Integer.parseInt(product.getPrice()));
        });
        order.setTotalAmount(total);
        orderRepository.save(order);
        return ResponseEntity.ok(order.toString());
    }
}
