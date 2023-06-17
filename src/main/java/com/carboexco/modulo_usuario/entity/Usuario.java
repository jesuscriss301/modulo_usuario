package com.carboexco.modulo_usuario.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 7)
    private String nombre;

    @Column(name = "`contraceña`", nullable = false, length = 64)
    private String contraceña;

    @Column(name = "codigo_radio", nullable = false, length = 9)
    private String codigoRadio;

}