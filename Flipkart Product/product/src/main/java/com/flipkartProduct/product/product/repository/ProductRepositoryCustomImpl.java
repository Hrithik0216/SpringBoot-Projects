package com.flipkartProduct.product.product.repository;

import com.flipkartProduct.product.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public boolean findBydataProductName(String productName) {
//        System.out.println(" from service layer: " + productName);
        Query query = new Query(Criteria.where("dataProductName").is(productName));
        Product product = mongoTemplate.findOne(query, Product.class);
        Optional<Product> productOptional = Optional.ofNullable(product);
        return productOptional.isPresent();
    }

    @Override
    public Product findBydataProductNameForProduct(String productName) {
        Query query = new Query(Criteria.where("dataProductName").is(productName));
        Product product = mongoTemplate.findOne(query, Product.class);
        Optional<Product> productOptional = Optional.ofNullable(product);
        return productOptional.isPresent()?productOptional.get():null;
    }
}
