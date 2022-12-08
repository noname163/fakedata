package com.nashtech.assignment.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.assignment.data.entities.ReturnAsset;

public interface ReturnAssetRepository extends JpaRepository<ReturnAsset, Long> {
    
}
