package com.carboexco.modulo_usuario.controller;

import com.carboexco.modulo_usuario.security.TokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidationController {

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String bearerToken) {

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Extrae el token de la cadena del encabezado
            String token = bearerToken.replace("Bearer ", "");

            // Obtiene la autenticación a partir del token JWT
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);

            // Establece la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            System.out.println("Token is valid");
            return ResponseEntity.status(HttpStatus.OK).body("Token is valid");
        }else {
            System.out.println("Token is invalid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        }
    }
}
