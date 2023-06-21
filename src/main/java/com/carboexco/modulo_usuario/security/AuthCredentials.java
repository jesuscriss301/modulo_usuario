package com.carboexco.modulo_usuario.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String codigo_Radio;
    private String password;
}
