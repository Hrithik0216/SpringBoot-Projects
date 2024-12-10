package com.flipkartProduct.product.Repository;

import com.flipkartProduct.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
//    List<Product> findListByPage(Pageable pageable);
}
