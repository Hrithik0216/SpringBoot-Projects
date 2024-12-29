package com.flipkartProduct.product.cart.service;

import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.model.CartItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartServiceInterface {
    ResponseEntity<CartDto> getCart(String userId);

    ResponseEntity<String> addToCart(Cart cart, String userId);

    ResponseEntity<String> removeFromCart(Cart cart);
    ResponseEntity<String> updateCart(Cart cart);

    Cart addToExistingCartOfExistingUser(String userId, List<CartItem> existingCartItem, List<CartItem> newCartItem);
}
