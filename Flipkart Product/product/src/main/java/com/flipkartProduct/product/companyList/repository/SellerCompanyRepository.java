package com.flipkartProduct.product.companyList.repository;

import com.flipkartProduct.product.companyList.model.SellerCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerCompanyRepository extends MongoRepository<SellerCompany, String> {
}
