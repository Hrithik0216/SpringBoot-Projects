package com.flipkartProduct.product.cart.repository;

import com.flipkartProduct.product.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String>, CartRepositoryCustom {
}
