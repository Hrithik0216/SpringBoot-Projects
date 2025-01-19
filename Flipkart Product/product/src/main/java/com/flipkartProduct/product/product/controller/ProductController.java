package com.flipkartProduct.product.product.controller;

import com.flipkartProduct.product.product.dto.ProductDTO;
import com.flipkartProduct.product.product.repository.ProductRepository;
import com.flipkartProduct.product.members.repository.MemberRepositoryCustom;
import com.flipkartProduct.product.product.service.ProductService;
import com.flipkartProduct.product.product.model.Product;
import com.flipkartProduct.product.members.model.User;
import com.flipkartProduct.product.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @Autowired
    @Qualifier("primaryMongoTemplate")
    MongoTemplate mongoTemplate;


    @Autowired
    MemberRepositoryCustom memberRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/dummyToCheckLogger")
    public ResponseEntity<?> dummyToCheckLogger(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            try {
                String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
                boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
                LOGGER.info("userRole: " + userRole + " , userId: " + userId);
                return ResponseEntity.ok().body("Super");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        LOGGER.error("Unauthorized user");
        return ResponseEntity.badRequest().build();
    }

    //Adding a single product by verifying if it exists
    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(HttpServletRequest request, @RequestBody Product product) {
        String productName = product.getDataProductName();
        String user = request.getHeader("user");
        if (user != null && user.equals("ROLE_ADMIN")) {
            if (productRepository.findBydataProductName(productName)) {
                return productService.increaseProductByQuantity(product);
            } else {
                return productService.postAProduct(product);
            }
        }
        LOGGER.error("Unauthorized user trying to add the product");
        return ResponseEntity.ok().body("Unauticaticated User");

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
    /*
     * Used this api for fetching all product details.
     * */

    @GetMapping("/getAllProductsById")
    public ResponseEntity<?> getAllProductsById(HttpServletResponse response, HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("The authorization token is not provided");
        }
        String token = authorization.substring(7);
        try {
            String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
            boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
            LOGGER.info("userRole: " + userRole + " , userId: " + userId);
            User user = memberRepository.findByUserId(userId);
            if (user == null) {
                LOGGER.info("User not found");
                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("User not found");
            }
            if (!userRole) {
                LOGGER.info("User does not have permission");
                return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body("User does not have permission");
            }
            return productService.getProducts();

        } catch (Exception e) {
            LOGGER.error("Internal Server Error" + e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    @GetMapping("/getProductsByCategory")
    public ResponseEntity<?> getProductsByCategory(HttpServletRequest request, HttpServletResponse response, @RequestParam String category) {
        if (category == null || category.length() <= 0) {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body("category is missing or invalid");
        }
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Authorization token is not provided or invalid");
        }
        String token = authorization.substring(7);
        try {
            String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
            boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
            LOGGER.info("userRole: " + userRole + " , userId: " + userId);
            User user = memberRepository.findByUserId(userId);
            if (user == null) {
                LOGGER.info("User not found");
                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("User not found");
            }
            if (!userRole) {
                LOGGER.info("User does not have permission");
                return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body("User does not have permission");
            }
            LOGGER.info("The userId and role is validated and getProductsByCategory endpoint is invoked");
            return productService.getProductsByCategory(category);

        } catch (Exception e) {
            LOGGER.error("Internal Server Error: "+e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/getAllProductsUsingPagination")
    public ResponseEntity<?> getAllProductsUsingPagination(HttpServletRequest request, HttpServletResponse response,
                                                           @RequestParam int pageNumber,
                                                           @RequestParam int size,
                                                           @RequestParam String direction,
                                                           @RequestParam String sortParam) {

        if (pageNumber < 0) {
            LOGGER.warn("The page number is less than 0 orinvalid");
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body("The page number is invalid");
        }
        if (size < 0) {
            LOGGER.warn("The size is less than 0 orinvalid");
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body("The size is invalid");
        }
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Authorization token is not provided or invalid");
        }
        String token = authorization.substring(7);
        try {
            String userId = JwtUtils.validateJwtTokenAndGetUserId(token);
            boolean userRole = JwtUtils.validateJwtTokenAndGetRoles(token);
            LOGGER.info("userRole: " + userRole + " , userId: " + userId);
            User user = memberRepository.findByUserId(userId);
            if (user == null) {
                LOGGER.info("User not found");
                return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("User not found");
            }
            if (!userRole) {
                LOGGER.info("User does not have permission");
                return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body("User does not have permission");
            }
            Sort.Direction sortDirection =Sort.Direction.ASC;
            try{
                sortDirection = Sort.Direction.fromString(direction);

            }catch (Exception e){
                LOGGER.warn("Invalid direction for sorting" + e.getMessage());
                return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body("Invalid direction for sorting" + e.getMessage());
            }
            LOGGER.info("The userId and role is validated and getAllProductsUsingPagination endpoint is invoked");
            Pageable pageable = PageRequest.of(pageNumber, size, sortDirection,sortParam);
            return productService.getAllProductsUsingPagination(pageable);
        } catch (Exception e) {
            LOGGER.error("Internal Server Error: "+ e.getMessage());
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).body("Internal server error");
        }


    }

    @GetMapping("/getProductByMatchoperation")
    public ResponseEntity<List<ProductDTO>> getProductByMatchoperation(@RequestParam String category) {
        return productService.getProductByMatchoperation(category);
    }

    @GetMapping("/getProductByMutipleMatchoperation")
    public ResponseEntity<List<ProductDTO>> getProductByMutipleMatchoperation(String category, long price) {
        return productService.getProductByMutipleMatchoperation(category, price);
    }

}
