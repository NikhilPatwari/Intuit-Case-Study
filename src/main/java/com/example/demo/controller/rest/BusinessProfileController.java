package com.example.demo.controller.rest;

import com.example.demo.models.BusinessProfile;
import com.example.demo.service.BusinessProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/businessProfile")
public class BusinessProfileController {
    @Autowired
    public BusinessProfileController(BusinessProfileManager service) {
        this.service = service;
    }

    private final BusinessProfileManager service;

    @GetMapping("findById")
    public ResponseEntity<BusinessProfile> getBusinessProfileById(@RequestParam String id) {
        return ResponseEntity.ok(service.getProfileById(id));
    }

    @PostMapping("create")
    public ResponseEntity<BusinessProfile> createBusinessProfile(@RequestBody BusinessProfile profile) {
        return ResponseEntity.ok(service.saveProfile(profile));
    }

    @PostMapping("update")
    public ResponseEntity<BusinessProfile> updateBusinessProfile(@RequestBody BusinessProfile profile) {
        return ResponseEntity.ok(service.updateProfile(profile));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Boolean> updateBusinessProfile(@RequestParam String id ) {
        return ResponseEntity.ok(service.deleteProfile(id));
    }
}
