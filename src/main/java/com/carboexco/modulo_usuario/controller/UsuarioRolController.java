package com.carboexco.modulo_usuario.controller;

import com.carboexco.modulo_usuario.entity.UsuarioRol;
import com.carboexco.modulo_usuario.repository.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/usuario-roles")
public class UsuarioRolController {

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    /**
     * Obtiene todos los registros de UsuarioRol.
     *
     * @return Lista de UsuarioRol.
     */
    @GetMapping
    public List<UsuarioRol> getAllUsuarioRoles() {
        return usuarioRolRepository.findAll();
    }

    /**
     * Obtiene un registro de UsuarioRol por su ID.
     *
     * @param idUsuarioRol ID del registro de UsuarioRol.
     * @return El registro de UsuarioRol correspondiente al ID, o null si no se encuentra.
     */
    @GetMapping("/{idUsuarioRol}")
    public UsuarioRol getUsuarioRolById(@PathVariable Long idUsuarioRol) {
        Optional<UsuarioRol> usuarioRol = usuarioRolRepository.findById(idUsuarioRol);

        if (usuarioRol.isPresent()) {
            return usuarioRol.get();
        }

        return null;
    }

    /**
     * Crea un nuevo registro de UsuarioRol.
     *
     * @param usuarioRol El objeto UsuarioRol a crear.
     * @return El registro de UsuarioRol creado.
     */
    @PostMapping
    public UsuarioRol createUsuarioRol(@RequestBody UsuarioRol usuarioRol) {
        return usuarioRolRepository.save(usuarioRol);
    }

    /**
     * Actualiza un registro de UsuarioRol existente.
     *
     * @param idUsuarioRol ID del registro de UsuarioRol a actualizar.
     * @param usuarioRol   El objeto UsuarioRol con los nuevos datos.
     * @return El registro de UsuarioRol actualizado, o null si no se encuentra.
     */
    @PutMapping("/{idUsuarioRol}")
    public UsuarioRol updateUsuarioRol(@PathVariable Long idUsuarioRol, @RequestBody UsuarioRol usuarioRol) {
        Optional<UsuarioRol> currentUsuarioRol = usuarioRolRepository.findById(idUsuarioRol);

        if (currentUsuarioRol.isPresent()) {
            UsuarioRol updatedUsuarioRol = currentUsuarioRol.get();
            updatedUsuarioRol.setIdUsuario(usuarioRol.getIdUsuario());
            updatedUsuarioRol.setIdRol(usuarioRol.getIdRol());
            return usuarioRolRepository.save(updatedUsuarioRol);
        }

        return null;
    }

    /**
     * Elimina un registro de UsuarioRol por su ID.
     *
     * @param idUsuarioRol ID del registro de UsuarioRol a eliminar.
     * @return El registro de UsuarioRol eliminado, o null si no se encuentra.
     */
    @DeleteMapping("/{idUsuarioRol}")
    public UsuarioRol deleteUsuarioRol(@PathVariable Long idUsuarioRol) {
        Optional<UsuarioRol> usuarioRol = usuarioRolRepository.findById(idUsuarioRol);

        if (usuarioRol.isPresent()) {
            UsuarioRol deletedUsuarioRol = usuarioRol.get();
            usuarioRolRepository.deleteById(idUsuarioRol);
            return deletedUsuarioRol;
        }

        return null;
    }
}

