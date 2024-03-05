package com.example.CursoSpringSecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CursoSpringSecurity.document.LoginRequest;
import com.example.CursoSpringSecurity.document.UserDocument;
import com.example.CursoSpringSecurity.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDocument request){
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage().toString(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage().toString(),HttpStatus.BAD_REQUEST);
        }        
    }

}
