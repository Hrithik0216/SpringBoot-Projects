package com.flipkartProduct.product.cart.controller;

import com.flipkartProduct.product.ApiResponse.ApiResponse;
import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.repository.CartRepository;
import com.flipkartProduct.product.cart.service.CartService;
import com.flipkartProduct.product.members.model.User;
import com.flipkartProduct.product.members.repository.MemberRepository;
import com.flipkartProduct.product.members.repository.MemberRepositoryCustom;
import com.flipkartProduct.product.product.repository.ProductRepository;
import com.flipkartProduct.product.utils.JwtUtils;
import com.mongodb.MongoException;
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
    ProductRepository productRepository;

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
    //@PostMapping("/addToCart")
//    public ResponseEntity<ApiResponse<Cart>> addToCart(HttpServletRequest request, HttpServletResponse response, @RequestBody Cart newCart) {
//        String authorization = request.getHeader("Authorization");
//
//        if(authorization==null || !authorization.startsWith("Bearer ")) {
//            LOGGER.warning("Authorization token is not provided or invalid");
//            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(new ApiResponse<>("Authorization token is not provided",null));
//        }
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            String token = authorization.substring(7);
//            try {
//                String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
//                boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
//                User user = memberRepository.findByUserId(userId);
//                if (user != null) {
//                    Optional<Cart> existingCartCheckWithUserId = Optional.ofNullable(cartRepository.findCartByUserId(userId));
//
//                    if (existingCartCheckWithUserId.isPresent()) {
//                        Cart existingCart = existingCartCheckWithUserId.get();
//                        Cart updatedCart = cartService.addToExistingCartOfExistingUser(
//                                userId,
//                                existingCart.getProductList(),
//                                newCart.getProductList()
//                        );
//                        updatedCart.setId(existingCart.getId());
//                        String orderedItemIdFromCart = newCart.getProductList().get(0).getProductId();
//                        if (orderedItemIdFromCart != null) {
//
//                            boolean isUpdated = productRepository.findByProductIdAndUpdateQuantity(orderedItemIdFromCart, newCart.getProductList().get(0).getQuantity());
//                            if (isUpdated) {
//                                System.out.println(orderedItemIdFromCart);
//                                cartRepository.save(updatedCart);
//                                ApiResponse<Cart> apiResponseBody = new ApiResponse<>("Added to an existing cart", updatedCart);
//                                return ResponseEntity.ok(apiResponseBody);
//                            }
//                        } else {
//                            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).build();
//                        }
//                    } else {
//                        newCart.setUser(userId); // Assuming `setUserId` is the correct method
//                        cartRepository.save(newCart);
//                        ApiResponse<Cart> apiResponseBody = new ApiResponse<>("Added to a new cart", newCart);
//                        return ResponseEntity.ok(apiResponseBody);
//                    }
//                } else {
//                    return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
//                }
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
//            }
//        }
//        LOGGER.info("Auhtorization token is not provided");
//        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
//    }

    @PostMapping("/addToCart")
    public ResponseEntity<ApiResponse<Cart>> addToCart(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody Cart newCart
    ) {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            LOGGER.warning("Authorization token is not provided or invalid");
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(new ApiResponse<>("Authorization token is missing or invalid", null));
        }

        String token = authorization.substring(7);

        try {
            String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
            boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
            User user = memberRepository.findByUserId(userId);

            if (user == null) {
                LOGGER.warning("User not found for userId: {}" + userId);
                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND)
                        .body(new ApiResponse<>("User not found", null));
            }

            Optional<Cart> existingCartOptional = Optional.ofNullable(cartRepository.findCartByUserId(userId));

            if (existingCartOptional.isPresent()) {
                // Update existing cart
                Cart existingCart = existingCartOptional.get();
                Cart updatedCart = cartService.addToExistingCartOfExistingUser(
                        userId,
                        existingCart.getProductList(),
                        newCart.getProductList()
                );
                updatedCart.setId(existingCart.getId());

                String orderedItemIdFromCart = newCart.getProductList().get(0).getProductId();

                if (orderedItemIdFromCart != null) {
                    boolean isUpdated = cartService.isUpdated(orderedItemIdFromCart,
                            newCart.getProductList().get(0).getQuantity());
                    if (isUpdated) {
                        cartRepository.save(updatedCart);
                        LOGGER.info("Product {} added to existing cart for user {}" + orderedItemIdFromCart + userId);
                        ApiResponse<Cart> responseBody = new ApiResponse<>("Added to an existing cart", updatedCart);
                        return ResponseEntity.ok(responseBody);
                    } else {
                        LOGGER.warning("Failed to update product quantity for productId: {}" + orderedItemIdFromCart);
                        return ResponseEntity.status(HttpServletResponse.SC_CONFLICT)
                                .body(new ApiResponse<>("Failed to update product quantity", null));
                    }
                } else {
                    LOGGER.warning("Product ID from cart is null");
                    return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST)
                            .body(new ApiResponse<>("Invalid product data", null));
                }
            } else {
                // Create new cart
                newCart.setUser(userId);
                cartRepository.save(newCart);
                LOGGER.info("New cart created for user {}" + userId);
                ApiResponse<Cart> responseBody = new ApiResponse<>("Added to a new cart", newCart);
                return ResponseEntity.ok(responseBody);
            }

        } catch (Exception e) {
            LOGGER.info("The error is" + e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error processing request", null));
        }
    }

    @GetMapping("/getMyCart")
    public ResponseEntity<?> getCart(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        LOGGER.info("Received request to get cart. Checking authorization...");

        if (authorization == null || !authorization.startsWith("Bearer ") || authorization.length() <= 7) {
            LOGGER.warning("Authorization token is missing or invalid");
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(new ApiResponse<>("The authorization token is not present or invalid", null));
        }

        String token = authorization.substring(7);
        LOGGER.info("Authorization token extracted successfully.");

        try {
            String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
            boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
            LOGGER.info("JWT token validated. User ID: {}, Has required role: {}" + userId + userRole);

            if (!userRole) {
                LOGGER.warning("User ID {} does not have required permissions" + userId);
                return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN)
                        .body(new ApiResponse<>("User does not have permission", null));
            }

            try {
                LOGGER.info("Fetching user details from database for user ID: {}" + userId);
                Optional<User> user = Optional.ofNullable(secondaryMongoTemplate.findById(userId, User.class));

                if (!user.isPresent()) {
                    LOGGER.warning("User not found in database for ID: {}" + userId);
                    return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND)
                            .body(new ApiResponse<>("User not found", null));
                }
                LOGGER.info("User found in database. Fetching cart details...");
                return cartService.getCart(userId);

            } catch (MongoException e) {
                LOGGER.info("Database error while fetching user data: {}" + e.getMessage());
                return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                        .body(new ApiResponse<>("Database error occurred", null));
            }

        } catch (Exception e) {
            LOGGER.info("Unexpected error occurred: {}" + e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Internal Server Error", null));
        }
    }
}