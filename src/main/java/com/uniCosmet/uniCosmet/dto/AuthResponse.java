package com.uniCosmet.uniCosmet.dto;

import com.uniCosmet.uniCosmet.model.Rol;

public class AuthResponse {

    private String token;
    private String nickname;
    private String rol;

    // Constructor, getters y setters
    public AuthResponse(String token, String nickname, String rol) {
        this.token = token;
        this.nickname = nickname;
        this.rol = rol;
    }

    // Getters y setters...


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
