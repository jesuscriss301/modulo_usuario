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

    /**
     * Obtiene la colección de permisos otorgados al usuario.
     *
     * @return La colección de permisos (vacía en este caso).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implementación personalizada para obtener los permisos del usuario
        // Collection usuarios =  UsuarioRolRepository.usuarioRolRepository.findById_IdUsuarioOrderById_IdUsuarioAsc();
        return Collections.emptyList();
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return usuario.getContracenia();
    }

    /**
     * Obtiene el nombre de usuario del usuario.
     *
     * @return El nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return usuario.getCodigoRadio();
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     *
     * @return true si la cuenta no ha expirado, false en caso contrario (siempre devuelve true en este caso).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario está bloqueada.
     *
     * @return true si la cuenta no está bloqueada, false en caso contrario (siempre devuelve true en este caso).
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario han expirado.
     *
     * @return true si las credenciales no han expirado, false en caso contrario (siempre devuelve true en este caso).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado.
     *
     * @return true si el usuario está habilitado, false en caso contrario (siempre devuelve true en este caso).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return usuario.getNombre();
    }
}
