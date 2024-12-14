package com.flipkartProduct.product.Repository;

import com.flipkartProduct.product.Repository.CutomImplementationRepository.ProductRepository.ProductRepositoryCustom;
import com.flipkartProduct.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> , ProductRepositoryCustom {
//    List<Product> findListByPage(Pageable pageable);
}
