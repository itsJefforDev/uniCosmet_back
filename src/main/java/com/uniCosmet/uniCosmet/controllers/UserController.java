package com.uniCosmet.uniCosmet.controllers;

import com.uniCosmet.uniCosmet.dto.AuthRequest;
import com.uniCosmet.uniCosmet.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserPatch(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserPatch(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
