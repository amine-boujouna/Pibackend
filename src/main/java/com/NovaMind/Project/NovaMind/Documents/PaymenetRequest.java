package com.NovaMind.Project.NovaMind.Documents;

public class PaymenetRequest {
    private Double amount;          // Montant du paiement
    private String paymentMethod;   // Méthode de paiement (ex : "CARTE", "PAYPAL", etc.)

    // Constructeurs, getters et setters

    public PaymenetRequest() {}

    public PaymenetRequest(Double amount, String paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
