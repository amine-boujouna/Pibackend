package com.NovaMind.Project.NovaMind.Repositories;

import com.NovaMind.Project.NovaMind.Documents.Payment;
import com.NovaMind.Project.NovaMind.Documents.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails,Long> {
}
