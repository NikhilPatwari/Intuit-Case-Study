package com.example.demo.repository;

import com.example.demo.models.BusinessProfile;

public interface BusinessProfileRepository {
    BusinessProfile findById(String id);

    BusinessProfile createProfile(BusinessProfile profile);

    BusinessProfile updateProfile(BusinessProfile profile);

    boolean deleteProfile(String id);
}
