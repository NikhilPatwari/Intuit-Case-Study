package com.example.demo.service.impl;

import com.example.demo.dto.Status;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.BusinessProfile;
import com.example.demo.repository.BusinessProfileRepository;
import com.example.demo.service.BusinessProfileManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessProfileManagerImpl implements BusinessProfileManager {
    private final BusinessProfileRepository repository;

    @Override
    public BusinessProfile getBusinessProfileById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(BusinessProfile.class.getSimpleName()));
    }

    @Override
    public Status saveProfile(BusinessProfile profile) {
        repository.save(profile);
        return Status.getSuccessStatus();
    }

    @Override
    public Status deleteProfile(String id) {
        repository.deleteById(id);
        return Status.getSuccessStatus();
    }
}
