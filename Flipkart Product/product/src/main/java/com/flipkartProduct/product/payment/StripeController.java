package com.flipkartProduct.product.payment;


import com.flipkartProduct.product.payment.DTO.CreatePaymentRequest;
import com.flipkartProduct.product.payment.DTO.StripeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stripe")
@RequiredArgsConstructor
public class StripeController {
    private final StripeService stripeService;

    @PostMapping("/create-payment")
    public ResponseEntity<StripeResponse> createPayment(@RequestBody CreatePaymentRequest createPaymentRequest) {
        StripeResponse stripeResponse = stripeService.createPayment(createPaymentRequest);
        return ResponseEntity
                .status(stripeResponse.getHttpStatus())
                .body(stripeResponse);
    }

    @GetMapping("/capture-payment")
    public ResponseEntity<StripeResponse> capturePayment(@RequestParam String sessionId) {
        StripeResponse stripeResponse = stripeService.capturePayment(sessionId);
        return ResponseEntity
                .status(stripeResponse.getHttpStatus())
                .body(stripeResponse);
    }
}
