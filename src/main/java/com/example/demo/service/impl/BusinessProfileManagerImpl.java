package com.example.demo.service.impl;

import com.example.demo.models.BusinessProfile;
import com.example.demo.repository.BusinessProfileRepository;
import com.example.demo.service.BusinessProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessProfileManagerImpl implements BusinessProfileManager {
    private final BusinessProfileRepository repository;

    @Autowired
    public BusinessProfileManagerImpl(BusinessProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public BusinessProfile getProfileById(String id) {
        return repository.findById(id);
    }

    @Override
    public BusinessProfile saveProfile(BusinessProfile profile) {
        return repository.createProfile(profile);
    }

    @Override
    public BusinessProfile updateProfile(BusinessProfile profile) {
        return repository.updateProfile(profile);
    }

    @Override
    public boolean deleteProfile(String id) {
        return repository.deleteProfile(id);
    }
}
