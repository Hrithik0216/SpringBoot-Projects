package com.flipkartProduct.product.payment;

import com.flipkartProduct.product.payment.DTO.CapturePaymentResponse;
import com.flipkartProduct.product.payment.DTO.CreatePaymentRequest;
import com.flipkartProduct.product.payment.DTO.CreatePaymentResponse;
import com.flipkartProduct.product.payment.DTO.StripeResponse;
import com.flipkartProduct.product.payment.utils.Constant;
import com.stripe.exception.StripeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import com.stripe.Stripe;

@Service
@Slf4j
public class StripeService {
    @Value("${secret_key}")
    private String secretKey;

    /**
     * Creates a new payment.
     *
     * @param createPaymentRequest Payment request object
     * @return Payment response object
     */
    public StripeResponse createPayment(CreatePaymentRequest createPaymentRequest) {
        // Set your secret key. Remember to switch to your live secret key in production!
        Stripe.apiKey = secretKey;

        // Create a PaymentIntent with the order amount and currency
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(createPaymentRequest.getName())
                        .build();

        // Create new line item with the above product data and associated price
        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency(createPaymentRequest.getCurrency())
                        .setUnitAmount(createPaymentRequest.getAmount())
                        .setProductData(productData)
                        .build();

        // Create new line item with the above price data
        SessionCreateParams.LineItem lineItem =
                SessionCreateParams
                        .LineItem.builder()
                        .setQuantity(createPaymentRequest.getQuantity())
                        .setPriceData(priceData)
                        .build();

        // Create new session with the line items
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(createPaymentRequest.getSuccessUrl())
                        .setCancelUrl(createPaymentRequest.getCancelUrl())
                        .addLineItem(lineItem)
                        .build();

        // Create new session
        Session session;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            e.printStackTrace();
            return StripeResponse
                    .builder()
                    .status(Constant.FAILURE)
                    .message("Payment session creation failed")
                    .httpStatus(400)
                    .data(null)
                    .build();
        }

        CreatePaymentResponse responseData = CreatePaymentResponse
                .builder()
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();

        return StripeResponse
                .builder()
                .status(Constant.SUCCESS)
                .message("Payment session created successfully")
                .httpStatus(200)
                .data(responseData)
                .build();
    }

    public StripeResponse capturePayment(String sessionId) {
        Stripe.apiKey = secretKey;

        try {
            Session session = Session.retrieve(sessionId);
            String status = session.getStatus();

            if (status.equalsIgnoreCase(Constant.STRIPE_SESSION_STATUS_SUCCESS)) {
                // Handle logic for successful payment
                log.info("Payment successfully captured.");
            }

            CapturePaymentResponse responseData = CapturePaymentResponse
                    .builder()
                    .sessionId(sessionId)
                    .sessionStatus(status)
                    .paymentStatus(session.getPaymentStatus())
                    .build();

            return StripeResponse
                    .builder()
                    .status(Constant.SUCCESS)
                    .message("Payment successfully captured.")
                    .httpStatus(200)
                    .data(responseData)
                    .build();
        } catch (StripeException e) {
            // Handle capture failure, log the error, and return false
            e.printStackTrace();
            return StripeResponse
                    .builder()
                    .status(Constant.FAILURE)
                    .message("Payment capture failed due to a server error.")
                    .httpStatus(500)
                    .data(null)
                    .build();
        }
    }
}
