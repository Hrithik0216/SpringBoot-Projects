package com.flipkartProduct.product.Service;

import com.flipkartProduct.product.DTO.ProductDTO;
import com.flipkartProduct.product.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductServiceInterface {
    ResponseEntity<String> increaseProductByQuantity(Product product);
    ResponseEntity<String> postAProduct(Product product);
    ResponseEntity<String> postProducts(List<Product> products);
    ResponseEntity<List<ProductDTO>> getProducts();
    ResponseEntity<List<Product>> getProductsByCategory(String category);
    ResponseEntity<List<ProductDTO>> getProductByMatchoperation(String category);
    ResponseEntity<List<ProductDTO>> getProductByMutipleMatchoperation(String category, long price);
    ResponseEntity<List<Product>> getAllProductsUsingPagination(Pageable pageable);
    ResponseEntity<List<Product>> getAllProductsUsingPaginationAndSorting(Pageable pageable);

}
