package com.flipkartProduct.product.cart.service;

import com.flipkartProduct.product.product.repository.ProductRepository;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.model.CartItem;
import com.flipkartProduct.product.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public boolean isUpdated(String productId, int orderedQuantity){
        return productRepository.findByProductIdAndUpdateQuantity(
                productId,
                orderedQuantity);
    }

    @Override
    public Cart findCartByUserId(String userId){
        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public void saveCart(Cart cart){
        cartRepository.save(cart);
    }

    @Override
    public ResponseEntity<?> getCart(String userId) {
        return cartRepository.findCartByCustomerId(userId);
    }

    @Override
    public Cart addToExistingCartOfExistingUser(String userId, List<CartItem> existingCartItem, List<CartItem> newCartItem) {
        Map<String, CartItem> map = existingCartItem.stream()
                .collect(Collectors.toMap(CartItem::getProductId, item -> item));

        for (CartItem temp : newCartItem) {
            CartItem result = map.get(temp.getProductId());
            if (result != null) {
                // Update quantity if the product already exists
                result.setQuantity(result.getQuantity() + temp.getQuantity());
            } else {
                // Add new product to the map
                map.put(temp.getProductId(), temp);
            }
        }
        return new Cart(userId, new ArrayList<>(map.values()));
    }
}
