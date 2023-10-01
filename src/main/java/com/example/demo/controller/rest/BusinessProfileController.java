package com.example.demo.controller.rest;

import com.example.demo.dto.Status;
import com.example.demo.models.BusinessProfile;
import com.example.demo.models.CreateProfileGrp;
import com.example.demo.models.UpdateProfileGrp;
import com.example.demo.service.BusinessProfileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("create")
    public ResponseEntity<Status> createBusinessProfile(@Validated(CreateProfileGrp.class) @RequestBody BusinessProfile profile, @RequestParam(required = false) String product) {
        return new ResponseEntity<>(service.createProfile(profile,product), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Status> updateBusinessProfile(@Validated(UpdateProfileGrp.class) @RequestBody BusinessProfile profile, @RequestParam(required = false) String product) {
        return ResponseEntity.ok(service.updateProfile(profile, product));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Status> deleteBusinessProfile(@RequestParam String id) {
        return ResponseEntity.ok(service.deleteProfile(id));
    }
}
