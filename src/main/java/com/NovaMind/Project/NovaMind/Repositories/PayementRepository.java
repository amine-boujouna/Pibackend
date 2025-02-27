package com.NovaMind.Project.NovaMind.Repositories;

import com.NovaMind.Project.NovaMind.Documents.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayementRepository extends JpaRepository<Payment,Long > {
    boolean existsByMemberShipIdAndIsSuccessful(Long membershipId, boolean isSuccessful);

}
