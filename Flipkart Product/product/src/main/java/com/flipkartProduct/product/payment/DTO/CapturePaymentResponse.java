package com.flipkartProduct.product.payment.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapturePaymentResponse {
    private String sessionId;
    private String sessionStatus;
    private String paymentStatus;
}
