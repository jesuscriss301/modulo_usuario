package com.carboexco.modulo_usuario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "usuario_rol")
public class UsuarioRol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    @Column(name = "id_rol", nullable = false)
    private Rol idRol;

}