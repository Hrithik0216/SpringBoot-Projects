package com.flipkartProduct.product.payment.DTO;

import lombok.Data;

@Data
public class CreatePaymentRequest {
    private Long amount;
    private Long quantity;
    private String currency;
    private String name;
    private String successUrl;
    private String cancelUrl;

}
