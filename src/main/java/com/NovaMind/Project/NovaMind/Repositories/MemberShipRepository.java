package com.NovaMind.Project.NovaMind.Repositories;

import com.NovaMind.Project.NovaMind.Documents.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberShipRepository extends JpaRepository<MemberShip,Long> {
}
