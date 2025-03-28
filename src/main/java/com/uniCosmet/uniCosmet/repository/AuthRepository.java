package com.uniCosmet.uniCosmet.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uniCosmet.uniCosmet.model.User;

public interface AuthRepository extends JpaRepository<User, Long>{
    User findByNickname(String nickname);
}
