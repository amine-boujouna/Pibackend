package com.NovaMind.Project.NovaMind.Documents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Long membershipId;
    private Double amount;
    private String duration; // "MOIS", "ANNEE", "SEMAINE"
    private String paymentMethod; // "CARTE", "PAYPAL"

    // Champs pour la carte bancaire
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    // Champ pour PayPal
    private String paypalTransactionId;

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPaypalTransactionId() {
        return paypalTransactionId;
    }

    public void setPaypalTransactionId(String paypalTransactionId) {
        this.paypalTransactionId = paypalTransactionId;
    }
}
