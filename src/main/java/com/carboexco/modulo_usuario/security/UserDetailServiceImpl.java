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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("UserDetailServiceImpl:  loadUserByUsername________________________");
        System.out.println(username);
        Usuario usuario = usuarios.findByCodigoRadio(username)
                .orElseThrow(()->new UsernameNotFoundException("el usuario de codigo " + username + " no existe."));



        return new UserDetailImpl(usuario);
    }
}
