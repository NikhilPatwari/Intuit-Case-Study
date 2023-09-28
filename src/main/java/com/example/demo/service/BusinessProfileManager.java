package com.example.demo.service;

import com.example.demo.dto.Status;
import com.example.demo.models.BusinessProfile;

public interface BusinessProfileManager {
    BusinessProfile getBusinessProfileById(String id);

    Status saveProfile(BusinessProfile profile);

    Status deleteProfile(String id);
}
