package com.flipkartProduct.product.orders.respository;

import com.flipkartProduct.product.orders.model.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderModel, String> {
}
