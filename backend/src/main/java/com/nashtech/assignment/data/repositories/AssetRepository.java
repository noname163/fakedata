package com.nashtech.assignment.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.assignment.data.entities.Asset;

public interface AssetRepository extends JpaRepository<Asset,Long> {
    
}
