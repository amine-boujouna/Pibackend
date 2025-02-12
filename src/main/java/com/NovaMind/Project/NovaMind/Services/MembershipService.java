package com.NovaMind.Project.NovaMind.Services;

import com.NovaMind.Project.NovaMind.Documents.MemberShip;
import com.NovaMind.Project.NovaMind.Repositories.MemberShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class MembershipService {
    private final MemberShipRepository membershipRepository;

    @Autowired
    public MembershipService(MemberShipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<MemberShip> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public MemberShip getMembershipById(Long id) {
        return membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
    }

    public MemberShip createMembership(MemberShip membership) {
        return membershipRepository.save(membership);
    }

    public MemberShip updateMembership(Long id, MemberShip membershipDetails) {
        MemberShip membership = getMembershipById(id);
        membership.setFees(membershipDetails.getFees());
        membership.setPaymentMethod(membershipDetails.getPaymentMethod());
        membership.setStatus(membershipDetails.getStatus());
        membership.setStart_date(membershipDetails.getStart_date());
        membership.setEnd_date(membershipDetails.getEnd_date());
        membership.setPricing(membershipDetails.getPricing());
        membership.setMembershipType(membershipDetails.getMembershipType());
        return membershipRepository.save(membership);
    }

    public void deleteMembership(Long id) {
        membershipRepository.deleteById(id);
    }
}

