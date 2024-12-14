package com.flipkartProduct.product.companyList.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SellerCompanyServiceInterface {
    ResponseEntity<String> addSellerCompany(Map<String,Map<String, Object>> sellerCompanies);
    public String getCompanies(HttpServletRequest request, HttpServletResponse response);
}
