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
                LOGGER.warning("User not found for userId: {}"+ userId);
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
                    boolean isUpdated = productRepository.findByProductIdAndUpdateQuantity(
                            orderedItemIdFromCart,
                            newCart.getProductList().get(0).getQuantity()
                    );

                    if (isUpdated) {
                        cartRepository.save(updatedCart);
                        LOGGER.info("Product {} added to existing cart for user {}"+ orderedItemIdFromCart+ userId);
                        ApiResponse<Cart> responseBody = new ApiResponse<>("Added to an existing cart", updatedCart);
                        return ResponseEntity.ok(responseBody);
                    } else {
                        LOGGER.warning("Failed to update product quantity for productId: {}"+ orderedItemIdFromCart);
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
                LOGGER.info("New cart created for user {}"+userId);
                ApiResponse<Cart> responseBody = new ApiResponse<>("Added to a new cart", newCart);
                return ResponseEntity.ok(responseBody);
            }

        } catch (Exception e) {
            LOGGER.info("The error is"+ e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error processing request", null));
        }
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