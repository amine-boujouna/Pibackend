package com.NovaMind.Project.NovaMind.Documents;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    private String cardNumber; // Numéro de carte bancaire (à stocker de manière sécurisée)
    private String expiryDate; // Date d'expiration de la carte
    private String cvv; // Code CVV (éviter de le stocker en clair)

    private String paypalTransactionId; // Si paiement via PayPal, stocker l'ID de transaction PayPal

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPaypalTransactionId() {
        return paypalTransactionId;
    }

    public void setPaypalTransactionId(String paypalTransactionId) {
        this.paypalTransactionId = paypalTransactionId;
    }
}
