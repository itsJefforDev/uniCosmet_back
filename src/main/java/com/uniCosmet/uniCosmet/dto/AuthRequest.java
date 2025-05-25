package com.uniCosmet.uniCosmet.dto;

import com.uniCosmet.uniCosmet.model.Rol;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

public class AuthRequest {
    private String nickname;
    private String password;

    // Getters y setters
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
