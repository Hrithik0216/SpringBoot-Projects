package com.flipkartProduct.product.Service;

import com.flipkartProduct.product.DTO.CompanyDTO;
import com.flipkartProduct.product.Repository.SellerCompanyRepository;
import com.flipkartProduct.product.model.SellerCompany;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SellerCompanyService implements SellerCompanyServiceInterface{
    @Autowired
    SellerCompanyRepository sellerCompanyRepository;

    @Override
    public ResponseEntity<String> addSellerCompany(Map<String, Map<String, Object>> sellerCompanies) {
        List<SellerCompany> sellerCompaniesList = new ArrayList<>();
        System.out.println(sellerCompanies);
        try{
            sellerCompanies.forEach((key, value)->{
                SellerCompany sellerCompany = new SellerCompany();
                sellerCompany.setCompanyPhone((String) value.get("companyPhone"));
                sellerCompany.setCompanyName((String) value.get("companyName"));
                sellerCompany.setCompanyAddress((String) value.get("companyAddress"));
                sellerCompaniesList.add(sellerCompany);
            });
            System.out.println(sellerCompaniesList);
            sellerCompanyRepository.saveAll(sellerCompaniesList);
            return ResponseEntity.ok("Uploaded" + sellerCompanies.size());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Error in adding SellerCompany");
        }
    }

    @Override
    public String getCompanies(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();

        sb.append("Request Method = [" + request.getMethod() + "], ");
        sb.append("Request URL Path = [" + request.getRequestURL() + "], ");
        sb.append("Header mail = ["+ request.getHeader("email") + "], ");

//        String headers =
//                Collections.list(request.getHeaderNames()).stream()
//                        .map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)) )
//                        .collect(Collectors.joining(", "));
//
//        if (headers.isEmpty()) {
//            sb.append("Request headers: NONE,");
//        } else {
//            sb.append("Request headers: ["+headers+"],");
//        }

//        String parameters =
//                Collections.list(request.getParameterNames()).stream()
//                        .map(p -> p + " : " + Arrays.asList( request.getParameterValues(p)) )
//                        .collect(Collectors.joining(", "));
//
//        if (parameters.isEmpty()) {
//            sb.append("Request parameters: NONE.");
//        } else {
//            sb.append("Request parameters: [" + parameters + "].");
//        }

        return sb.toString();
    }

}
