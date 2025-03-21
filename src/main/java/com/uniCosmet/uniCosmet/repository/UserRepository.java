package com.uniCosmet.uniCosmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniCosmet.uniCosmet.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
