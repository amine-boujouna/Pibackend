package com.NovaMind.Project.NovaMind.controller;

import com.NovaMind.Project.NovaMind.Documents.Payment;
import com.NovaMind.Project.NovaMind.Services.PaymentService;
import com.NovaMind.Project.NovaMind.Services.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private StripePaymentService stripePaymentService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Map<String, Object> request) {
        if (!request.containsKey("amount")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Required parameter 'amount' is missing"));
        }

        Double amount = ((Number) request.get("amount")).doubleValue();
        String currency = "eur"; // ou autre devise par défaut

        try {
            PaymentIntent intent = stripePaymentService.createPaymentIntent(amount, currency);
            return ResponseEntity.ok(Map.of("clientSecret", intent.getClientSecret()));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
    @PostMapping("/pay")
    public ResponseEntity<?> processPayment(@RequestBody Payment request) {
        if (request.getAmount() <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Montant invalide"));
        }

        if (request.getPaymentMethod() == null || request.getPaymentMethod().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Méthode de paiement non spécifiée"));
        }

        Payment payment = paymentService.recordPayment(
                request.getMembershipId(),
                request.getAmount(),
                request.getDuration(),
                request.getPaymentMethod()
        );

        String message = "Paiement réussi pour l'abonnement ID: " + payment.getMembershipId() + ". Montant: " + payment.getAmount() + "€.";
        return ResponseEntity.ok(Map.of("message", message));
    }

}
