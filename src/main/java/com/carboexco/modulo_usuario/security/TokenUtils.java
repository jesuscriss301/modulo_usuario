package com.carboexco.modulo_usuario.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private final static String ACCEESS_TOKEN_SECRET = "aTgwuv8RZKiuGeqsIU2EFoTzu5YTTvZSCiln26ER9XE=";
    private final  static Long ACCESS_TOKEN_VALIDITY_SECONDS = 259200L;

    /**
     * Crea un token JWT utilizando el nombre y el código de radio proporcionados.
     *
     * @param nombre      El nombre del usuario.
     * @param codigo_radio El código de radio del usuario.
     * @return El token JWT generado.
     */
    public static String createToken(String nombre, String codigo_radio){

        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis()+ expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(codigo_radio)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCEESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    /**
     * Obtiene la autenticación a partir de un token JWT.
     *
     * @param token El token JWT.
     * @return El objeto UsernamePasswordAuthenticationToken si el token es válido, o null si el token es inválido.
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCEESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String codigo_radio = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(codigo_radio,null, Collections.emptyList());
        } catch (JwtException e) {
            // El token es inválido o ha expirado
            return null;
        }
    }
}
