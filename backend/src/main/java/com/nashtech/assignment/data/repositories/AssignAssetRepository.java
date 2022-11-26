package com.nashtech.assignment.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.assignment.data.entities.AssignAsset;

public interface AssignAssetRepository extends JpaRepository<AssignAsset,Long>  {
    
}
