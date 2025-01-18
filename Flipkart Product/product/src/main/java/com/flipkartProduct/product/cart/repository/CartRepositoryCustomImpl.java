package com.flipkartProduct.product.cart.repository;

import com.flipkartProduct.product.product.repository.ProductRepository;
import com.flipkartProduct.product.cart.DTO.CartDto;
import com.flipkartProduct.product.cart.model.Cart;
import com.flipkartProduct.product.cart.model.CartItem;
import com.flipkartProduct.product.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class CartRepositoryCustomImpl implements CartRepositoryCustom {
    @Autowired
    @Qualifier("primaryMongoTemplate")
    MongoTemplate primaryMongoTemplate;

    @Autowired
    ProductRepository productRepository;


    @Override
    public ResponseEntity<?> findCartByCustomerId(String customerId) {
        Query query = new Query(Criteria.where("userId").is(customerId)); // Updated field name
        Optional<Cart> cartOptional = Optional.ofNullable(primaryMongoTemplate.findOne(query, Cart.class));
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public Cart findCartByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Optional<Cart> cartResult = Optional.ofNullable(primaryMongoTemplate.findOne(query,Cart.class));
        if(cartResult.isPresent()){
            return cartResult.get();
        }else{
            return null;
        }
    }

}
