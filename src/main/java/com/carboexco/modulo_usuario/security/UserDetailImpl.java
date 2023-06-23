package com.carboexco.modulo_usuario.security;

import com.carboexco.modulo_usuario.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailImpl implements UserDetails {


    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //Collection usuarios =  UsuarioRolRepository.usuarioRolRepository.findById_IdUsuarioOrderById_IdUsuarioAsc();
       return Collections.emptyList();
    }

    @Override
    public String getPassword() {

        return usuario.getContracenia();
    }

    @Override
    public String getUsername() {

        System.out.println("UserDetailImpl:     getUsername => "+ usuario.getCodigoRadio());
        return usuario.getCodigoRadio();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre(){

        System.out.println("UserDetailImpl:     getNombre => "+ usuario.getNombre());
        return usuario.getNombre();
    }
}
