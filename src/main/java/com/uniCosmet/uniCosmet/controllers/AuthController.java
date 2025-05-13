package com.uniCosmet.uniCosmet.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniCosmet.uniCosmet.dto.LoginRequest;
import com.uniCosmet.uniCosmet.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-back.onrender.com"})
    public ResponseEntity<?> login(@RequestParam String nickname, @RequestParam String password) {
        LoginRequest loginRequest = new LoginRequest(nickname, password);
        boolean authenticated = authService.authenticate(loginRequest.getNickname(), loginRequest.getPassword());
        if (authenticated) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login ok");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
