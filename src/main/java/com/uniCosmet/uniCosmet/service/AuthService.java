package com.uniCosmet.uniCosmet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.repository.AuthRepository;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    /* Método para autenticar al usuario
    public boolean authenticate(String nickname, String password) {
        System.out.println(nickname);
        //User user = authRepository.findByNickname(nickname);
        if (user != null && user.getPassword().equals(password)) {
            
            return true; // Inicio de sesión exitoso
        }
        return false; // Credenciales incorrectas
    }

     */
}
