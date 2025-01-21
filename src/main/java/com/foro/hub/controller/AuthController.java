package com.foro.hub.controller;

import com.foro.hub.dto.LoginRequest;
import com.foro.hub.service.AuthService;
import com.foro.hub.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    // Endpoint para autenticar y generar el token
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequest loginRequest) {
        // Autenticar al usuario (se asume que AuthService maneja la lógica de
        // autenticación)
        authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        // Generar el token JWT
        return tokenService.generateToken(loginRequest.getUsername());
    }
}