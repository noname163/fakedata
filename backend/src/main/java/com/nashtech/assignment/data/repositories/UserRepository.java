package com.nashtech.assignment.data.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.assignment.data.constants.EUserType;
import com.nashtech.assignment.data.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLocation(String location, Pageable pageable);
    List<User> findByUsernameOrStaffCodeContaining(String name, String staffCode);
    List<User> findByType(EUserType type);
}
