package com.NovaMind.Project.NovaMind.Services;
import com.NovaMind.Project.NovaMind.Documents.CourseModule;
import com.NovaMind.Project.NovaMind.Documents.PaymenetRequest;
import com.NovaMind.Project.NovaMind.Documents.Payment;
import com.NovaMind.Project.NovaMind.Documents.PaymentDetails;
import com.NovaMind.Project.NovaMind.Repositories.ModuleRepository;
import com.NovaMind.Project.NovaMind.Repositories.PayementRepository;
import com.NovaMind.Project.NovaMind.Repositories.PaymentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class PaymentService {

    @Autowired
    private PayementRepository paymentRepository;


    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    public Payment recordPayment(Long membershipId, Double amount, String duration, String paymentMethod) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDuration(duration);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }


    public Payment processPayment(Payment payment, PaymentDetails paymentDetails) {
        // Vérification que le membership est bien défini
        if (payment.getMembership() == null) {
            throw new IllegalArgumentException("Le membre associé au paiement est manquant");
        }

        // Assigner la date du paiement
        payment.setPaymentDate(LocalDateTime.now());

        // Enregistrer le paiement
        Payment savedPayment = paymentRepository.save(payment);

        // Lier les détails du paiement au paiement
        paymentDetails.setPayment(savedPayment);

        // Enregistrer les détails du paiement
        paymentDetailsRepository.save(paymentDetails);

        return savedPayment;
    }
    public Payment processPaymentmodule(Long courseModuleId, PaymenetRequest paymentRequest) {
        // 1. Vérifier si le module existe
        CourseModule module = moduleRepository.findById(courseModuleId).orElseThrow(() -> new RuntimeException("Module not found"));

        // 2. Créer un paiement
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setIspaied(true); // Paiement validé

        // 3. Lier le paiement avec le module
        payment.setModules(Collections.singletonList(module));

        // 4. Sauvegarder le paiement
        return paymentRepository.save(payment);
    }
}



