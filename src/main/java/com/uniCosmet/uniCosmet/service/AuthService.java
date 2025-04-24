package com.uniCosmet.uniCosmet.service;

import com.uniCosmet.uniCosmet.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.repository.AuthRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public Optional<User> login(String nickname) {
        return authRepository.findByNickname(nickname);

    }
}
