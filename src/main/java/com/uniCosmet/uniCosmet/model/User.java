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
    private String nickName_user;
    private String password_user;
    private String rol_user;

    public User(){

    }
    
    public User(Integer id, String name, Integer age, String email, String nickName_user, String password_user,
            String rol_user) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.nickName_user = nickName_user;
        this.password_user = password_user;
        this.rol_user = rol_user;
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

    public String getNickName_user() {
        return nickName_user;
    }

    public void setNickName_user(String nickName_user) {
        this.nickName_user = nickName_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getRol_user() {
        return rol_user;
    }

    public void setRol_user(String rol_user) {
        this.rol_user = rol_user;
    }

}
