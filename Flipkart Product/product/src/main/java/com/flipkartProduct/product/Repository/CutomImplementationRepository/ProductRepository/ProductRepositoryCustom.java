package com.flipkartProduct.product.Repository.CutomImplementationRepository.ProductRepository;

import com.flipkartProduct.product.model.Product;

public interface ProductRepositoryCustom {
    boolean findBydataProductName(String productName);
    Product findBydataProductNameForProduct(String productName);
}
