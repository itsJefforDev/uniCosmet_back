package com.uniCosmet.uniCosmet.service;

import com.uniCosmet.uniCosmet.dto.UserDTO;
import com.uniCosmet.uniCosmet.exception.DuplicateNicknameException;
import com.uniCosmet.uniCosmet.exception.UserNotFoundException;
import com.uniCosmet.uniCosmet.model.Rol;
import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));
        return convertToDTO(user);
    }

    public UserDTO createUser(User user) {
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new DuplicateNicknameException("El nickname ya está en uso");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateNicknameException("El email ya está en uso");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRol() == null) {
            user.setRol(Rol.USER); // Rol por defecto
        }

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con ID: " + id));

        if (!user.getNickname().equals(userDetails.getNickname()) &&
                userRepository.existsByNickname(userDetails.getNickname())) {
            throw new DuplicateNicknameException("El nickname ya está en uso");
        }

        user.setName(userDetails.getName());
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        user.setNickname(userDetails.getNickname());
        user.setRol(userDetails.getRol());

        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    //Método para editar parcialmente un usuario
    public UserDTO updateUserPatch(Integer id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Solo actualizamos los campos que no sean nulos en el objeto updatedUser
        if (updatedUser.getName() != null) {
            if (!existingUser.getNickname().equals(updatedUser.getNickname()) &&
                    userRepository.existsByNickname(updatedUser.getNickname())) {
                throw new DuplicateNicknameException("El nickname ya está en uso");
            }

            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getAge() != null) {
            existingUser.setAge(updatedUser.getAge());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getUsername() != null) {
            existingUser.setNickname(updatedUser.getUsername());
        }

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        if (updatedUser.getRol() != null) {
            existingUser.setRol(updatedUser.getRol());
        }

        userRepository.save(existingUser);
        return convertToDTO(existingUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setNickname(user.getNickname());
        dto.setRol(user.getRol().name());
        dto.setPassword(user.getPassword());

        return dto;
    }

    /*
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Obtener todos los productos
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un producto por su ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Guardar un usuario
    public User saveUser(AuthRequest request) {
        User user = new User();
        user.setNickname(request.getNickname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRol(request.getRolAsEnum()); // Usa la conversión segura

        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());

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
        existingUser.setNickname(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRol(updatedUser.getRol());

        return userRepository.save(existingUser);
    }

         Método para editar parcialmente un usuario
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
            if (updatedUser.getUsername() != null) {
                existingUser.setNickname(updatedUser.getUsername());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getRol() != null) {
                existingUser.setRol(updatedUser.getRol());
            }
    
            return userRepository.save(existingUser);
        }

         */

}
