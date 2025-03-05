package com.NovaMind.Project.NovaMind.Services;
import com.NovaMind.Project.NovaMind.Documents.Payment;
import com.NovaMind.Project.NovaMind.Documents.PaymentDetails;
import com.NovaMind.Project.NovaMind.Repositories.PayementRepository;
import com.NovaMind.Project.NovaMind.Repositories.PaymentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PayementRepository paymentRepository;


    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

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


}
