package com.flipkartProduct.product.Service;

import com.flipkartProduct.product.DTO.ProductDTO;
import com.flipkartProduct.product.Repository.ProductRepository;
import com.flipkartProduct.product.model.Product;
import com.mongodb.client.MongoClient;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService implements ProductServiceInterface {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private MongoClient mongo;

    @Override
    public ResponseEntity<List<Product>> getAllProductsUsingPagination(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return ResponseEntity.ok(result.getContent());
    }

    @Override
    public ResponseEntity<List<Product>> getAllProductsUsingPaginationAndSorting(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return ResponseEntity.ok(result.getContent());
    }

    @Override
    public ResponseEntity<String> postAProduct(Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Product saved successfully");
    }

    @Override
    public ResponseEntity<List<Product>> postProducts(List<Product> products) {
        return ResponseEntity.ok(productRepository.saveAll(products));
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> result = productRepository.findAll().stream().map(product -> {
            ProductDTO pr = new ProductDTO();
            pr.setProduct(product.getProduct());
            pr.setPrice(product.getPrice());
            return pr;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByCategory(String category) {
        Query query = new Query();
        Criteria criteria = Criteria.where("category").is(category);
                //.and("price").gte(60000);
        query.addCriteria(criteria);
        List<Product> result= mongoTemplate.find(query, Product.class);
        List<Product> finalResult= result.stream().filter(product-> Integer.parseInt(product.getPrice())>60000).collect(Collectors.toList());
        if(finalResult.size()>0){
            return ResponseEntity.ok(finalResult);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getProductByMatchoperation(String category) {
        Criteria criteria = Criteria.where("category").is(category);

        MatchOperation matchOperation = new MatchOperation(criteria);
//        ProjectionOperation projectionOperation = Aggregation.project("product","price");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation
                //projectionOperation
        );
        AggregationResults<ProductDTO> results = mongoTemplate.aggregate(aggregation, "product", ProductDTO.class);
//        System.out.println(results.getMappedResults());
        return ResponseEntity.ok(results.getMappedResults());
    }
    @Override
    public ResponseEntity<List<ProductDTO>> getProductByMutipleMatchoperation(String category, long price) {
        // 1st Aggregation: Match Operation by category
        MatchOperation matchOperation = Aggregation.match(
                Criteria.where("category").is(category)
        );

        // 2nd Aggregation: Project operation to include "product", "price", and convert "price" to numeric
        ProjectionOperation projectionOperation = Aggregation.project("category", "product", "price")
                .andExpression("{$toDouble: '$price'}").as("numericPrice");  // Convert price to numeric

        // 3rd Aggregation: Match the price to filter by the numeric version of "price"
        MatchOperation priceFilterOperation = Aggregation.match(
                Criteria.where("numericPrice").gte(price)  // Compare the numeric version of "price"
        );

        // 4th Aggregation: Group by category and count the number of products
//        GroupOperation groupOperation = Aggregation.group("category")
//                .count().as("mobileCategoryTypeCount");  // Count products per category

        // Create the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, projectionOperation, priceFilterOperation
//                groupOperation
        );

        // Execute the aggregation
        AggregationResults<ProductDTO> result = mongoTemplate.aggregate(aggregation, "product", ProductDTO.class);

        // Log raw aggregation result to debug
        System.out.println("Aggregation Result: " + result.getMappedResults());

        // Return the results as a ResponseEntity
        List<ProductDTO> productDTOs = result.getMappedResults().stream().map(document -> {
            ProductDTO dto = new ProductDTO();
            dto.setProduct(document.getProduct());
            dto.setPrice(document.getPrice());
//            dto.setMobileCategoryTypeCount(document.getMobileCategoryTypeCount());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(productDTOs);
    }

}