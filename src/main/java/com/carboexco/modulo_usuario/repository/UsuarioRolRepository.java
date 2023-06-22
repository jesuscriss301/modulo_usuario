package com.carboexco.modulo_usuario.repository;

import com.carboexco.modulo_usuario.entity.UsuarioRol;
import com.carboexco.modulo_usuario.entity.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {
    Collection<UsuarioRol> findById_IdUsuarioOrderById_IdUsuarioAsc(Integer idUsuario);

    Optional<UsuarioRol> findFirstById_IdUsuarioAndId_IdRolOrderById_IdUsuarioAsc(Integer idUsuario, Integer idRol);


}