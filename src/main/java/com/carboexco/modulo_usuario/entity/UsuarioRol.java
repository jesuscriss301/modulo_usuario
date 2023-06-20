package com.carboexco.modulo_usuario.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario_rol")
public class UsuarioRol {
    @EmbeddedId
    private UsuarioRolId id;

    //TODO [JPA Buddy] generate columns from DB
}