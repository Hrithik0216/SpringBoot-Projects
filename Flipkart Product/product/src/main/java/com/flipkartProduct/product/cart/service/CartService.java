package com.flipkartProduct.product.cart.service;

import com.flipkartProduct.product.product.repository.ProductRepository;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.model.CartItem;
import com.flipkartProduct.product.cart.repository.CartRepository;
import com.flipkartProduct.product.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

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
    public ResponseEntity<?> getCart(String userId) {
        return cartRepository.findCartByCustomerId(userId);
    }

    @Override
    public ResponseEntity<String> addToCart(Cart cart, String userId) {
        List<CartItem> cartItems = cart.getProductList();
        long quantity = cartItems.stream().mapToLong(CartItem::getQuantity).sum();
        String productId = cartItems.stream()
                .map(CartItem::getProductId)
                .findFirst()
                .orElse(null);
        System.out.print(productId);
        if (productId == null) {
            return ResponseEntity.badRequest().body("Product ID is missing in the request.");
        }
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Product ID is invalid.");
        }
        Product product = productOptional.get();
        if (product.getQuantity() < quantity) {
            return ResponseEntity.badRequest().body("Insufficient stock for product:");
        }
        cartRepository.save(new Cart(userId, cart.getProductList()));
        return ResponseEntity.ok().body("Product added to Cart");
    }

    @Override
    public ResponseEntity<String> removeFromCart(Cart cart) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateCart(Cart cart) {
        return null;
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
