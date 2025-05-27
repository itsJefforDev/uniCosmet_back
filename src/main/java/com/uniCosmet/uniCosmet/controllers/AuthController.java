package com.uniCosmet.uniCosmet.controllers;

import com.uniCosmet.uniCosmet.dto.AuthRequest;
import com.uniCosmet.uniCosmet.dto.AuthResponse;
import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
