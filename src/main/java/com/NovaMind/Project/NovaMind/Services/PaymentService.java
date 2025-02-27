package com.NovaMind.Project.NovaMind.Services;
import com.NovaMind.Project.NovaMind.Documents.Payment;
import com.NovaMind.Project.NovaMind.Repositories.PayementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Autowired
    private PayementRepository paymentRepository;

    public Payment recordPayment(Long membershipId, Double amount, String duration, String paymentMethod) {
        Payment payment = new Payment();
        payment.setMembershipId(membershipId);
        payment.setAmount(amount);
        payment.setDuration(duration);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
