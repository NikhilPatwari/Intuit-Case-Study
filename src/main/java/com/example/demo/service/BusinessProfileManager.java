package com.example.demo.service;

import com.example.demo.models.BusinessProfile;

public interface BusinessProfileManager {
    BusinessProfile getProfileById(String id);

    BusinessProfile saveProfile(BusinessProfile profile);

    BusinessProfile updateProfile(BusinessProfile profile);

    boolean deleteProfile(String id);
}
