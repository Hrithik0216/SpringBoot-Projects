package com.flipkartProduct.product.cart.repository;

import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartRepositoryCustom {
    ResponseEntity<?> findCartByCustomerId(String customerId);
    Cart findCartByUserId(String userId);
}
