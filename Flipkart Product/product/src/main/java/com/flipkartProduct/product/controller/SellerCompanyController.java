package com.flipkartProduct.product.controller;

import com.flipkartProduct.product.Service.SellerCompanyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/company")
public class SellerCompanyController {

    @Autowired
    SellerCompanyService sellerCompanyService;
    @PostMapping("/addCompanies")
    public ResponseEntity<String> addCompany(@RequestBody Map<String,Map<String,Object>> listOfCompanies) {
        return sellerCompanyService.addSellerCompany(listOfCompanies);
    }
    @GetMapping("/getCompanies")
    public String getCompanies(HttpServletRequest request, HttpServletResponse response) {
        return sellerCompanyService.getCompanies(request, response);
    }
}
