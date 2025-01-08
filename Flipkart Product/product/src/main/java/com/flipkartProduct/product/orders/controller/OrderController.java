package com.flipkartProduct.product.orders.controller;

import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.repository.CartRepository;
import com.flipkartProduct.product.members.model.User;
import com.flipkartProduct.product.members.repository.MemberRepositoryCustom;
import com.flipkartProduct.product.orders.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepositoryCustom memberRepository;


    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, String> orderDetails){
        String userId = request.getHeader("userId");
        String role = request.getHeader("role");
        String cartId = orderDetails.get("cartId");
        Optional<User> user = Optional.ofNullable(memberRepository.findByID(userId, role));
        if(user.isPresent()){
            Optional<Cart> cart = cartRepository.findById(cartId);
            System.out.println(cartId);
            if(cart.isPresent()){
                return orderService.placeOrder(cart.get());
            }else{
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
