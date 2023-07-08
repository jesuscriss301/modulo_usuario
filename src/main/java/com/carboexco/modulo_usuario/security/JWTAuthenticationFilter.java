package com.carboexco.modulo_usuario.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Intenta autenticar las credenciales enviadas en la solicitud.
     *
     * @param request  La solicitud HTTP.
     * @param response La respuesta HTTP.
     * @return El objeto Authentication que representa la autenticación exitosa.
     * @throws AuthenticationException Si la autenticación falla.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();

        try {
            // Convierte el cuerpo de la solicitud JSON en un objeto AuthCredentials utilizando ObjectMapper.
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
            // Manejo de errores de lectura del cuerpo de la solicitud.
        }

        // Crea un token de autenticación con las credenciales proporcionadas.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authCredentials.getCodigo_Radio(),
                authCredentials.getPassword(),
                Collections.emptyList());

        // Autentica el token utilizando el AuthenticationManager y retorna el resultado.
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    /**
     * Se llama después de una autenticación exitosa para generar un token JWT y establecerlo en la respuesta.
     *
     * @param request    La solicitud HTTP.
     * @param response   La respuesta HTTP.
     * @param chain      El FilterChain.
     * @param authResult El objeto Authentication que representa la autenticación exitosa.
     * @throws IOException      Si ocurre un error al escribir la respuesta.
     * @throws ServletException Si ocurre un error en el servlet.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // Obtiene los detalles del usuario autenticado.
        UserDetailImpl userDetails = (UserDetailImpl) authResult.getPrincipal();

        // Crea un token JWT utilizando los detalles del usuario autenticado.
        String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());

        // Agrega el token JWT en el encabezado de autorización de la respuesta.
        response.addHeader("Authorization", "Bearer " + token);
        /*
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Origin", "http//192.168.1.135:*");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://192.168.1.135:*","http://localhost:*"));
        corsConfiguration.addAllowedMethod("GET, POST, PUT, DELETE");
        corsConfiguration.addAllowedHeader("Content-Type, Content-Length, Host,");
        corsConfiguration.setAllowCredentials(true);

        response.setHeader("Access-Control-Allow-Origin", corsConfiguration.getAllowedOrigins().get(0));
        response.setHeader("Access-Control-Allow-Methods", corsConfiguration.getAllowedMethods().toString());
        response.setHeader("Access-Control-Allow-Headers", corsConfiguration.getAllowedHeaders().toString());
        response.setHeader("Access-Control-Allow-Credentials", String.valueOf(corsConfiguration.getAllowCredentials()));
        */
        // Escribe la respuesta y continúa con la cadena de filtros.
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
