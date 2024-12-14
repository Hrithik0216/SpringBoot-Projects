package com.flipkartProduct.product.product.repository;

import com.flipkartProduct.product.product.model.Product;

public interface ProductRepositoryCustom {
    boolean findBydataProductName(String productName);
    Product findBydataProductNameForProduct(String productName);
}
