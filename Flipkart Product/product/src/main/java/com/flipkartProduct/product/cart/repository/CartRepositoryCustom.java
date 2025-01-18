package com.flipkartProduct.product.cart.repository;

import com.flipkartProduct.product.cart.model.Cart;
import org.springframework.http.ResponseEntity;

public interface CartRepositoryCustom {
    ResponseEntity<?> findCartByCustomerId(String customerId);
    Cart findCartByUserId(String userId);
}
