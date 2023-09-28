package com.example.demo.repository;

import com.example.demo.models.BusinessProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusinessProfileRepository extends MongoRepository<BusinessProfile, String> {
}
