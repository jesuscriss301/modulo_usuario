package com.carboexco.modulo_usuario.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    /**
     * Filtra las solicitudes entrantes para verificar la presencia de un token JWT en el encabezado de autorización.
     * Si se encuentra un token válido, establece la autenticación en el contexto de seguridad.
     *
     * @param request     La solicitud HTTP.
     * @param response    La respuesta HTTP.
     * @param filterChain El FilterChain.
     * @throws ServletException Si ocurre un error en el servlet.
     * @throws IOException      Si ocurre un error de E/S.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Obtiene el token JWT del encabezado de autorización
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Extrae el token de la cadena del encabezado
            String token = bearerToken.replace("Bearer ", "");

            // Obtiene la autenticación a partir del token JWT
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);

            // Establece la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }

        // Continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
