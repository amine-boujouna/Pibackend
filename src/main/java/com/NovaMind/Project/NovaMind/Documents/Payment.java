package com.NovaMind.Project.NovaMind.Documents;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String duration; // "MOIS", "ANNEE", "SEMAINE"
    private String paymentMethod; // "CARTE", "PAYPAL", etc.
    private LocalDateTime paymentDate;
    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private PaymentDetails paymentDetails; // Détails du paiement
    @ManyToOne // Chaque paiement peut être lié à un abonnement
    @JoinColumn(name = "membership_id", referencedColumnName = "id") // Relier au champ "id" de l'entité Membership
    private MemberShip membership; // Relation avec l'entité Membership
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberShip getMembership() {
        return membership;
    }

    public void setMembership(MemberShip membership) {
        this.membership = membership;
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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
