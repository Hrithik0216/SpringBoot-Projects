package com.flipkartProduct.product.product.controller;

import com.flipkartProduct.product.product.dto.ProductDTO;
import com.flipkartProduct.product.product.repository.ProductRepository;
import com.flipkartProduct.product.members.repository.MemberRepositoryCustom;
import com.flipkartProduct.product.product.service.ProductService;
import com.flipkartProduct.product.product.model.Product;
import com.flipkartProduct.product.members.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    @Qualifier("primaryMongoTemplate")
    MongoTemplate mongoTemplate;


    @Autowired
    MemberRepositoryCustom memberRepository;

    @Autowired
    ProductRepository productRepository;

    //Adding a single product by verifying if it exists
    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(HttpServletRequest request, @RequestBody Product product) {
        String productName = product.getDataProductName();
        String user = request.getHeader("user");
        if (user != null && user.equals("ROLE_ADMIN")) {
            if (productRepository.findBydataProductName(productName)) {
                return productService.increaseProductByQuantity(product);
            }
        }
        return productService.postAProduct(product);
    }

    @PostMapping("/addMultipleProducts")
    public ResponseEntity<String> addProducts(HttpServletRequest request, @RequestBody List<Product> products) {
        String user = request.getHeader("user");
        if (user != null && user.equals("ROLE_ADMIN")) {
            productService.postProducts(products);
            return ResponseEntity.ok().body("Added Products Successfully");
        }
        return ResponseEntity.badRequest().body("User is not admin");

    }
    //

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(HttpServletResponse response, HttpServletRequest request) {
        String userName = request.getHeader("userName");
        String role = request.getHeader("role");
        User user = memberRepository.findByUsername(role, userName);
        if (user != null) {
            return productService.getProducts();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
    }

    @GetMapping("/getProductsByCategory")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/getProductByMatchoperation")
    public ResponseEntity<List<ProductDTO>> getProductByMatchoperation(@RequestParam String category) {
        return productService.getProductByMatchoperation(category);
    }

    @GetMapping("/getProductByMutipleMatchoperation")
    public ResponseEntity<List<ProductDTO>> getProductByMutipleMatchoperation(String category, long price) {
        return productService.getProductByMutipleMatchoperation(category, price);
    }


    @GetMapping("/getAllProductsUsingPagination")
    public ResponseEntity<?> getAllProductsUsingPagination(

            @RequestParam int pageNumber,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);
        return productService.getAllProductsUsingPagination(pageable);
    }

    @GetMapping("/getAllProductsUsingPaginationAndSorting")
    public ResponseEntity<List<Product>> getAllProductsUsingPaginationAndSorting(
            @RequestParam Sort.Direction direction,
            @RequestParam String sortParam,
            @RequestParam int pageNumber,
            @RequestParam int size) {

        Sort sort = Sort.by(direction, sortParam);
        Pageable pageable = PageRequest.of(pageNumber, size, sort);
        return productService.getAllProductsUsingPaginationAndSorting(pageable);
    }

}
