package com.example.CursoSpringSecurity.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CursoSpringSecurity.document.LoginRequest;
import com.example.CursoSpringSecurity.document.UserDocument;
import com.example.CursoSpringSecurity.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    
    public AuthService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(LoginRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDocument user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            String token = jwtService.generateToken(user);
            return new AuthResponse(token);
        } catch (Exception e) {
            return new AuthResponse(e.getMessage().toString());
        }
    }

    public AuthResponse register(UserDocument request){
        try {
            Optional<UserDocument> findUser = userRepository.findByUsername(request.getUsername());
            if(findUser.isPresent()){
                return new AuthResponse("User already exists");
            }
            request.setPassword(passwordEncoder.encode(request.getPassword()));
            String token = jwtService.generateToken(request);
            userRepository.save(request);
            return new AuthResponse(token);
        } catch (Exception e) {
            return new AuthResponse(e.getMessage().toString());
        }
    }

}
