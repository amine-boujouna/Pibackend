package com.NovaMind.Project.NovaMind.controller;

import com.NovaMind.Project.NovaMind.Documents.*;
import com.NovaMind.Project.NovaMind.Exception.ResourceNotFoundException;
import com.NovaMind.Project.NovaMind.Repositories.CourseRepository;
import com.NovaMind.Project.NovaMind.Repositories.MemberShipRepository;
import com.NovaMind.Project.NovaMind.Repositories.ModuleRepository;
import com.NovaMind.Project.NovaMind.Services.MembershipService;
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
    @Autowired
    private MemberShipRepository memberShipRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModuleRepository courseModuleRepository;

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



        String message = "Paiement réussi pour l'abonnement ID: " + "€.";
        return ResponseEntity.ok(Map.of("message", message));
    }


    @PostMapping("/payModule/{moduleId}")
    public ResponseEntity<Payment> payForModule(@PathVariable Long moduleId, @RequestBody PaymenetRequest paymentRequest) {
        Payment payment = paymentService.processPaymentmodule(moduleId, paymentRequest);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/process/{membershipId}")
    public Payment processPayment(@PathVariable("membershipId") Long membershipId, @RequestBody PaymentRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setDuration(request.getDuration());
        payment.setPaymentMethod(request.getPaymentMethod());

        // Récupérer le membre associé avec l'ID du membership passé dans l'URL
        MemberShip membership = memberShipRepository.findById(membershipId)
                .orElseThrow(() -> new IllegalArgumentException("Membre non trouvé"));

        payment.setMembership(membership); // Assigner le membre au paiement

        // Enregistrer les détails du paiement
        PaymentDetails paymentDetails = new PaymentDetails();
        if ("CARTE".equalsIgnoreCase(request.getPaymentMethod())) {
            paymentDetails.setCardNumber(request.getCardNumber());
            paymentDetails.setExpiryDate(request.getExpiryDate());
            paymentDetails.setCvv(request.getCvv());
        } else if ("PAYPAL".equalsIgnoreCase(request.getPaymentMethod())) {
            paymentDetails.setPaypalTransactionId(request.getPaypalTransactionId());
        }

        // Traiter le paiement
        return paymentService.processPayment(payment, paymentDetails);
    }


}
