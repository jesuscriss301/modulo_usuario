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
     * @param idRol ID del registro de Rol.
     * @param idUsuario ID del registro de Usuario.
     * @return El registro de UsuarioRol correspondiente al ID, o null si no se encuentra.
     */
    @GetMapping("/{idUsuario}/{idRol}")
    public UsuarioRol getUsuarioRolById(@PathVariable Integer idUsuario,@PathVariable Integer idRol) {
        Optional<UsuarioRol> usuarioRol = usuarioRolRepository.findFirstById_IdUsuarioAndId_IdRolOrderById_IdUsuarioAsc(idUsuario,idRol);

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
     * @param idRol ID del registro de Rol.
     * @param idUsuario ID del registro de Usuario.
     * @param usuarioRol   El objeto UsuarioRol con los nuevos datos.
     * @return El registro de UsuarioRol actualizado, o null si no se encuentra.
     */
    @PutMapping("/{idUsuarioRol}")
    public UsuarioRol updateUsuarioRol(@PathVariable Integer idUsuario,@PathVariable Integer idRol, @RequestBody UsuarioRol usuarioRol) {
        Optional<UsuarioRol> currentUsuarioRol =  usuarioRolRepository.findFirstById_IdUsuarioAndId_IdRolOrderById_IdUsuarioAsc(idUsuario,idRol);

        if (currentUsuarioRol.isPresent()) {
            UsuarioRol updatedUsuarioRol = currentUsuarioRol.get();
            updatedUsuarioRol.setId(usuarioRol.getId());
            return usuarioRolRepository.save(updatedUsuarioRol);
        }

        return null;
    }

    /**
     * Elimina un registro de UsuarioRol por su ID.
     *
     * @param idUsuario ID del registro de Usuario a eliminar.
     * @param idRol ID del registro de rol a eliminar.
     * @return El registro de UsuarioRol eliminado, o null si no se encuentra.
     */
    @DeleteMapping("/{idUsuarioRol}")
    public UsuarioRol deleteUsuarioRol(@PathVariable Integer idUsuario,@PathVariable Integer idRol) {
        Optional<UsuarioRol> usuarioRol = usuarioRolRepository.findFirstById_IdUsuarioAndId_IdRolOrderById_IdUsuarioAsc(idUsuario,idRol);

        if (usuarioRol.isPresent()) {
            UsuarioRol deletedUsuarioRol = usuarioRol.get();
            usuarioRolRepository.deleteById(deletedUsuarioRol.getId());
            return deletedUsuarioRol;
        }

        return null;
    }
}

