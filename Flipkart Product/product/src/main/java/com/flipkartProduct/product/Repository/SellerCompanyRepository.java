package com.flipkartProduct.product.Repository;

import com.flipkartProduct.product.model.SellerCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerCompanyRepository extends MongoRepository<SellerCompany, String> {
}
