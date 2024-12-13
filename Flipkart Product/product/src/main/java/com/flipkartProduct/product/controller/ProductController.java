package com.flipkartProduct.product.controller;

import com.flipkartProduct.product.DTO.ProductDTO;
import com.flipkartProduct.product.Service.CutomImplementationRepository.MemberRepositoryCustom;
import com.flipkartProduct.product.Service.CutomImplementationRepository.MemberRepositoryCustomImpl;
import com.flipkartProduct.product.Service.ProductService;
import com.flipkartProduct.product.model.Product;
import com.flipkartProduct.product.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    MongoTemplate secondaryMongoTemplate;

    @Autowired
    MemberRepositoryCustom memberRepository;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProducts(@RequestBody Product product) {
        return productService.postAProduct(product);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> products) {
        return productService.postProducts(products);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(HttpServletResponse response, HttpServletRequest request) {
        String userName = request.getHeader("userName");
        User user = memberRepository.findByUsername(userName);
        user.getRoles().forEach(System.out::println);



        if (user != null) {
            return productService.getProducts();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("Hrithik is not there");
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
