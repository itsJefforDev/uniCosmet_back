package com.uniCosmet.uniCosmet.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uniCosmet.uniCosmet.model.User;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long>{
    Optional<User> findByNickname(String nickname);
}
