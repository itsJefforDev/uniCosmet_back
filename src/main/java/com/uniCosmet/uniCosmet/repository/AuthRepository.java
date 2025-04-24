package com.uniCosmet.uniCosmet.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uniCosmet.uniCosmet.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long>{
    Optional<User> findByNickname(String nickname);
}
