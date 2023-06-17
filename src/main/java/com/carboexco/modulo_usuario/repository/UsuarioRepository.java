package com.carboexco.modulo_usuario.repository;

import com.carboexco.modulo_usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}