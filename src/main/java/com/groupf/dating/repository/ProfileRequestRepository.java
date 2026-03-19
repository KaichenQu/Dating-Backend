package com.groupf.dating.repository;

import com.groupf.dating.model.ProfileOptimizationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfileRequestRepository extends JpaRepository<ProfileOptimizationRequest, UUID> {

    List<ProfileOptimizationRequest> findByUserId(String userId);
}
