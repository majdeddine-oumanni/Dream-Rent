package com.rent.backend.auth;


import com.rent.backend.Configuration.JwtService;
import com.rent.backend.Model.Admin;
import com.rent.backend.Model.Owner;
import com.rent.backend.Model.Tenant;
import com.rent.backend.Model.User;
import com.rent.backend.Repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse register(RegisterRequest request) {
        User user;

        // Decide which subclass to create based on role
        switch (request.getRole()) {
            case OWNER -> user = new Owner();
            case TENANT -> user = new Tenant();
            case ADMIN -> user = new Admin();

            default -> throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }

        user.setFirstName(request.getFirstName()); // or request.getUsername()
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBanned(request.isBanned());
        user.setCountry(request.getCountry());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setPhone(request.getPhone());

        repository.save(user); // use appropriate repository for the subclass

        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setBanned(user.isBanned());
        response.setCountry(user.getCountry());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        return response;
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse();

        response.setToken(jwtToken);
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setBanned(user.isBanned());
        response.setCountry(user.getCountry());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        return response;
    }
}
