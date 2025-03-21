package com.uniCosmet.uniCosmet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public Optional<User> obtenerProductoPorId(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    public User guardarProducto(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
