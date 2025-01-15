package com.flipkartProduct.product.product.repository;

import com.flipkartProduct.product.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> , ProductRepositoryCustom {
//    List<Product> findListByPage(Pageable pageable);
}
