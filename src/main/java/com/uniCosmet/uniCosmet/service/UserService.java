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

    // Método para editar un usuario
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar los campos del usuario
        existingUser.setName(updatedUser.getName());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setNickname(updatedUser.getNickname());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRol(updatedUser.getRol());

        return userRepository.save(existingUser);
    }

        // Método para editar parcialmente un usuario
        public User updateUserPatch(Long id, User updatedUser) {
            User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            // Solo actualizamos los campos que no sean nulos en el objeto updatedUser
            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }
            if (updatedUser.getAge() != null) {
                existingUser.setAge(updatedUser.getAge());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getNickname() != null) {
                existingUser.setNickname(updatedUser.getNickname());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getRol() != null) {
                existingUser.setRol(updatedUser.getRol());
            }
    
            return userRepository.save(existingUser);
        }
}
