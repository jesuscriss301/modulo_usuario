package com.carboexco.modulo_usuario.repository;

import com.carboexco.modulo_usuario.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}