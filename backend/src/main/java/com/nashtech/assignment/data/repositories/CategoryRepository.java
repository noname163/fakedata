package com.nashtech.assignment.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nashtech.assignment.data.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
