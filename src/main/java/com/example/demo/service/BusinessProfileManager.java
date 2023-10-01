package com.example.demo.service;

import com.example.demo.dto.Status;
import com.example.demo.models.BusinessProfile;

import java.util.concurrent.ExecutionException;

public interface BusinessProfileManager {
    BusinessProfile getBusinessProfileById(String id);

    Status deleteProfile(String id);

    Status createProfile(BusinessProfile profile, String product);

    Status updateProfile(BusinessProfile profile, String product);
}
