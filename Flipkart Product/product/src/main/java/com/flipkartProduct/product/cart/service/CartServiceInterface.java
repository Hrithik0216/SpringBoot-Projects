package com.flipkartProduct.product.cart.service;

import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import org.springframework.http.ResponseEntity;

public interface CartServiceInterface {
    ResponseEntity<CartDto> getCart(String userId);

    ResponseEntity<String> addToCart(Cart cart, String userId);

    ResponseEntity<String> removeFromCart(Cart cart);
    ResponseEntity<String> updateCart(Cart cart);
}
