package com.flipkartProduct.product.product.repository;

import com.flipkartProduct.product.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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
        return productOptional.isPresent() ? productOptional.get() : null;
    }

    @Override
    public String findByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Product result = mongoTemplate.findOne(query, Product.class);
        Optional<Product> optionalProduct = Optional.ofNullable(result);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean findByProductIdAndUpdateQuantity(String productId, int orderedQuantity) {
        try {
            Optional<Product> findProduct = Optional.ofNullable(mongoTemplate.findById(productId, Product.class));
            long currentQuantity = findProduct.get().getQuantity();
            Query query = new Query(Criteria.where("id").is(productId));
            if (currentQuantity > orderedQuantity) {
                Update update = new Update().set("quantity", currentQuantity - orderedQuantity);
                mongoTemplate.updateFirst(query, update, Product.class);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
