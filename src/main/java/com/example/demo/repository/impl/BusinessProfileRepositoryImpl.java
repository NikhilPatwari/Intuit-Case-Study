package com.example.demo.repository.impl;

import com.example.demo.models.BusinessProfile;
import com.example.demo.repository.BusinessProfileRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessProfileRepositoryImpl implements BusinessProfileRepository {

    private final MongoTemplate dao;

    @Autowired
    public BusinessProfileRepositoryImpl(MongoTemplate dao) {
        this.dao = dao;
    }

    @Override
    public BusinessProfile findById(String id) {
        return dao.findById(id, BusinessProfile.class);
    }

    @Override
    public BusinessProfile createProfile(BusinessProfile profile) {
        return dao.save(profile);
    }

    @Override
    public BusinessProfile updateProfile(BusinessProfile profile) {
        Criteria criteria = Criteria.where("_id").is(profile.getId());
        Query query = new Query(criteria);
        Update update = new Update();
        UpdateResult res =  dao.updateFirst(query,update, BusinessProfile.class);
        return null;
    }

    @Override
    public boolean deleteProfile(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = dao.remove(query, BusinessProfile.class);
        return true;
    }
}
