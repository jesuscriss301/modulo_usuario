package com.carboexco.modulo_usuario.repository;

import com.carboexco.modulo_usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCodigoRadio(String codigoRadio);

}