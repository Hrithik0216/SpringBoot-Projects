package com.flipkartProduct.product.orders.controller;

import com.flipkartProduct.product.ApiResponse.ApiResponse;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.repository.CartRepository;
import com.flipkartProduct.product.findingMemberUtils.FindingMemberUtills;
import com.flipkartProduct.product.members.model.User;
import com.flipkartProduct.product.members.repository.MemberRepositoryCustom;
import com.flipkartProduct.product.orders.model.OrderRequestBody;
import com.flipkartProduct.product.orders.service.OrderService;
import com.flipkartProduct.product.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = Logger.getLogger(OrderController.class.getName());

    @Autowired
    OrderService orderService;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepositoryCustom memberRepository;

    @Autowired
    FindingMemberUtills findingMemberUtills;

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(HttpServletResponse response, HttpServletRequest request, @RequestBody OrderRequestBody order) {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            LOGGER.warning("Authorization header is incorrect");
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(new ApiResponse<>("The auhtorization token is not provided or invalid", null));
        }
        String token = authorization.substring(7);
        try {
            String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
            boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
            User user = findingMemberUtills.findByUserId(userId);

            if (user == null) {
                LOGGER.warning("User not found");
                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(new ApiResponse<>("User not found", null));
            }
            if (!userRole) {
                LOGGER.warning("The user does not have any authorized role");
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(new ApiResponse<>("The user is not authorized", null));
            }
            return orderService.placeOrder(order);
        } catch (Exception e) {
            LOGGER.warning("Internal servor err: " + e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body(new ApiResponse<>("Internal Server err", null));
        }
    }
}
