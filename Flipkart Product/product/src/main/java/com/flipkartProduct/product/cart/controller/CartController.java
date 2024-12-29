package com.flipkartProduct.product.cart.controller;

import com.flipkartProduct.product.ApiResponse.ApiResponse;
import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.repository.CartRepository;
import com.flipkartProduct.product.cart.service.CartService;
import com.flipkartProduct.product.members.model.User;
import com.flipkartProduct.product.members.repository.MemberRepository;
import com.flipkartProduct.product.members.repository.MemberRepositoryCustom;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    MemberRepositoryCustom memberRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    private MongoTemplate secondaryMongoTemplate;

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

    /*
     * Used this api
     *
     * */
    @PostMapping("/addToCart")
    public ResponseEntity<ApiResponse<Cart>> addToCart(HttpServletRequest request, HttpServletResponse response, @RequestBody Cart newCart) {
        String userId = request.getHeader("userId");
        String role = request.getHeader("role");
        User user = memberRepository.findByID(userId, role);

        if (user != null) {
            Optional<Cart> existingCartCheckWithUserId = Optional.ofNullable(cartRepository.findCartByUserId(userId));

            if (existingCartCheckWithUserId.isPresent()) {
                Cart existingCart = existingCartCheckWithUserId.get();
                Cart updatedCart = cartService.addToExistingCartOfExistingUser(
                        userId,
                        existingCart.getProductList(),
                        newCart.getProductList()
                );
                updatedCart.setId(existingCart.getId());
                cartRepository.save(updatedCart);
                ApiResponse<Cart> apiResponseBody = new ApiResponse<>("Added to an existing cart", updatedCart);;
                return ResponseEntity.ok(apiResponseBody);
            } else {
                newCart.setUser(userId); // Assuming `setUserId` is the correct method
                cartRepository.save(newCart);
                ApiResponse<Cart> apiResponseBody = new ApiResponse<>("Added to a new cart", newCart);
                return ResponseEntity.ok(apiResponseBody);
            }
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
    }


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


}