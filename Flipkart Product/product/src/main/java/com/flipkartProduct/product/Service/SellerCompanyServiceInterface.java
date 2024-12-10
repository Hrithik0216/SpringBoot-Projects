package com.flipkartProduct.product.Service;

import com.flipkartProduct.product.DTO.CompanyDTO;
import com.flipkartProduct.product.model.SellerCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface SellerCompanyServiceInterface {
    ResponseEntity<String> addSellerCompany(Map<String,Map<String, Object>> sellerCompanies);
    public String getCompanies(HttpServletRequest request, HttpServletResponse response);
}
