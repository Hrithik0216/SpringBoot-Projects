package com.flipkartProduct.product.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class StripeCongfig {
    @Value("${secret_key}")
    private String stripeKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeKey;
    }
}
