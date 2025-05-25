package com.uniCosmet.uniCosmet.security;

import com.uniCosmet.uniCosmet.dto.AuthResponse;
import com.uniCosmet.uniCosmet.dto.AuthRequest;
import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.model.Rol;
import com.uniCosmet.uniCosmet.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra un nuevo usuario en el sistema
     * @param user Datos del usuario a registrar
     * @return AuthResponse con el token JWT generado
     */
    public AuthResponse register(User user) {
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new RuntimeException("El nickname ya está en uso");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        // Creación manual del usuario sin @Builder
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setNickname(user.getNickname());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRol(user.getRol() != null ? user.getRol() : Rol.USER);

        userRepository.save(newUser);

        String jwtToken = jwtService.generateToken(newUser);

        // Creación manual de AuthResponse sin @Builder
        AuthResponse response = new AuthResponse(jwtToken,newUser.getNickname(),newUser.getRol().name());
        return response;
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getNickname(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByNickname(request.getNickname())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(user);

        // Creación manual de AuthResponse sin @Builder
        AuthResponse response = new AuthResponse(jwtToken,user.getNickname(),user.getRol().name());
        return response;
    }

}
