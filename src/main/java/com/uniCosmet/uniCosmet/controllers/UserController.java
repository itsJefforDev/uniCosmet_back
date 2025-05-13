package com.uniCosmet.uniCosmet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // Obtener todos los productos
    @GetMapping
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-back.onrender.com"})
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-back.onrender.com"})
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-back.onrender.com"})
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Crear un nuevo producto
    @PutMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-back.onrender.com"})
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser); // Devuelve el usuario actualizado
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-back.onrender.com"})
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    // Endpoint para editar parcialmente un usuario
    @PatchMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-back.onrender.com"})
    public ResponseEntity<User> updateUserPatch(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUserPatch(id, user);
        return ResponseEntity.ok(updatedUser); // Devuelve el usuario actualizado
    }
}
