package com.flipkartProduct.product.cart.controller;

import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.service.CartService;
import com.flipkartProduct.product.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    private MongoTemplate secondaryMongoTemplate;

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

    @GetMapping("getMyCart/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable("userId") String userId) {
        Optional<User> user = Optional.of(secondaryMongoTemplate.findById(userId, User.class));
        if (user.isPresent()) {
            System.out.println("Found the user");
            return cartService.getCart(userId);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("addToMyCart/{userId}")
    public ResponseEntity<String> addToCart(HttpServletRequest request, @PathVariable("userId") String userId, @RequestBody Cart cart) {
        Optional<User> user = Optional.ofNullable(secondaryMongoTemplate.findById(userId, User.class));
        String userRole = request.getHeader("user");
        if (user.isPresent() && userRole.equals("ROLE_USER")) {
            return cartService.addToCart(cart,String.valueOf(user.get().getId()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
