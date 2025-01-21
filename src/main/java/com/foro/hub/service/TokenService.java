package com.foro.hub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // Método para generar un token JWT
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username) // Establece el nombre de usuario como sujeto del token
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration)) // Establece la fecha de expiración
                .sign(Algorithm.HMAC256(secret)); // Firma el token con el algoritmo HMAC256 y la clave secreta
    }

    // Método para validar el token JWT
    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret)) // Verifica la firma usando el algoritmo y la clave secreta
                    .build()
                    .verify(token);
            return true; // Si no ocurre excepción, el token es válido
        } catch (Exception e) {
            return false; // Si ocurre una excepción, el token no es válido
        }
    }

    // Método para obtener el nombre de usuario desde el token
    public String getUsernameFromToken(String token) {
        return JWT.decode(token).getSubject();
    }
}