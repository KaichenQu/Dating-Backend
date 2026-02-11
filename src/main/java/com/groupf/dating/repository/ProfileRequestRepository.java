package com.groupf.dating.repository;

import com.groupf.dating.model.ProfileOptimizationRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRequestRepository extends MongoRepository<ProfileOptimizationRequest, String> {

    List<ProfileOptimizationRequest> findByUserId(String userId);
}
