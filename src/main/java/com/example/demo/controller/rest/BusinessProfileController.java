package com.example.demo.controller.rest;

import com.example.demo.dto.Status;
import com.example.demo.models.BusinessProfile;
import com.example.demo.service.BusinessProfileManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/businessProfile")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessProfileController {

    private final BusinessProfileManager service;

    @GetMapping("findById")
    public ResponseEntity<BusinessProfile> getBusinessProfileById(@RequestParam String id) {
        return ResponseEntity.ok(service.getBusinessProfileById(id));
    }

    @PostMapping("save")
    public ResponseEntity<Status> createBusinessProfile(@Valid @RequestBody BusinessProfile profile) {
        return ResponseEntity.ok(service.saveProfile(profile));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Status> deleteBusinessProfile(@RequestParam String id) {
        return ResponseEntity.ok(service.deleteProfile(id));
    }
}
