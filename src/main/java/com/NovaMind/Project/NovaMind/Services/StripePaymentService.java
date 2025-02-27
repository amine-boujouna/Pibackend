package com.NovaMind.Project.NovaMind.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StripePaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public PaymentIntent createPaymentIntent(Double amount, String currency) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (amount * 100))
                .setCurrency(currency)
                .addPaymentMethodType("card")
                .build();

        return PaymentIntent.create(params);
    }

}


