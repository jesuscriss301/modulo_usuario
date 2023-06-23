package com.carboexco.modulo_usuario.security;

import com.carboexco.modulo_usuario.entity.Usuario;
import com.carboexco.modulo_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarios;

    /**
     * Carga los detalles del usuario utilizando el nombre de usuario como referencia.
     *
     * @param username El nombre de usuario del usuario.
     * @return Los detalles del usuario (implementación de UserDetails) si el usuario existe.
     * @throws UsernameNotFoundException Si el usuario no existe.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarios.findByCodigoRadio(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con código " + username + " no existe."));

        return new UserDetailImpl(usuario);
    }
}
