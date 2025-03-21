package com.uniCosmet.uniCosmet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Obtener todos los productos
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un producto por su ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Guardar un producto
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Eliminar un producto por su ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
