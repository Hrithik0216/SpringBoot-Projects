package com.flipkartProduct.product.cart.controller;

import com.flipkartProduct.product.ApiResponse.ApiResponse;
import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.repository.CartRepository;
import com.flipkartProduct.product.cart.service.CartService;
import com.flipkartProduct.product.members.model.User;
import com.flipkartProduct.product.members.repository.MemberRepository;
import com.flipkartProduct.product.members.repository.MemberRepositoryCustom;
import com.flipkartProduct.product.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger LOGGER = Logger.getLogger(CartController.class.getName());

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
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            try {
                String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
                boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
                System.out.println("userRole: " + userRole);
                User user = memberRepository.findByUserId(userId);
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
                        ApiResponse<Cart> apiResponseBody = new ApiResponse<>("Added to an existing cart", updatedCart);
                        ;
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
            } catch (Exception e) {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
            }
        }
        LOGGER.info("Auhtorization token is not provided");
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
    }

    @GetMapping("/getMyCart")
    public ResponseEntity<CartDto> getCart(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
            boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
            if (userRole) {
                try {
                    Optional<User> user = Optional.of(secondaryMongoTemplate.findById(userId, User.class));
                    if (user.isPresent()) {
                        return cartService.getCart(userId);
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                } catch (Exception e) {
                    LOGGER.info("The err is" + e);
                }
            }
        }
        LOGGER.info("Authorization is not provide to get cartdetails");
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
    }
}