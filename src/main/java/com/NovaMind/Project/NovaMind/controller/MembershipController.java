package com.NovaMind.Project.NovaMind.controller;

import com.NovaMind.Project.NovaMind.Documents.MemberShip;
import com.NovaMind.Project.NovaMind.Documents.Pricing;
import com.NovaMind.Project.NovaMind.Repositories.MemberShipRepository;
import com.NovaMind.Project.NovaMind.Services.MembershipService;
import com.NovaMind.Project.NovaMind.Services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@CrossOrigin("*")
public class MembershipController {
    private final MembershipService membershipService;
    private final PricingService pricingService;

    @Autowired
    public MembershipController(MembershipService membershipService,PricingService pricingService) {
        this.membershipService = membershipService;
        this.pricingService=pricingService;
    }

    @GetMapping
    public List<MemberShip> getAllMemberships() {
        return membershipService.getAllMemberships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberShip> getMembershipById(@PathVariable Long id) {
        return ResponseEntity.ok(membershipService.getMembershipById(id));
    }

    @PostMapping
    public ResponseEntity<MemberShip> createMembership(@RequestBody MemberShip membership) {
        return ResponseEntity.status(HttpStatus.CREATED).body(membershipService.createMembership(membership));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberShip> updateMembership(@PathVariable("id") Long id, @RequestBody MemberShip membershipDetails) {
        return ResponseEntity.ok(membershipService.updateMembership(id, membershipDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable("id") Long id) {
        membershipService.deleteMembership(id);
        return ResponseEntity.noContent().build();
    }




}
