package com.uniCosmet.uniCosmet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremento en MySQL
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String nickname;
    private String password;
    private String rol;

    public User(){

    }
    
    public User(Integer id, String name, Integer age, String email, String nickname, String password,
            String rol) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol_user) {
        this.rol = rol_user;
    }

}
