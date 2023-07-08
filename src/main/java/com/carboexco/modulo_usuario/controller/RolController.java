package com.carboexco.modulo_usuario.controller;


import com.carboexco.modulo_usuario.entity.Rol;
import com.carboexco.modulo_usuario.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://192.168.1.135:*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    /**
     * Obtiene todos los roles.
     *
     * @return Lista de roles.
     */
    @GetMapping
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    /**
     * Obtiene un rol por su ID.
     *
     * @param idRol ID del rol.
     * @return El rol correspondiente al ID, o null si no se encuentra.
     */
    @GetMapping("/{idRol}")
    public Rol getRolById(@PathVariable int idRol) {
        Optional<Rol> rol = rolRepository.findById(idRol);

        if (rol.isPresent()) {
            return rol.get();
        }

        return null;
    }

    /**
     * Crea un nuevo rol.
     *
     * @param rol El objeto Rol a crear.
     * @return El rol creado.
     */
    @PostMapping
    public Rol createRol(@RequestBody Rol rol) {
        return rolRepository.save(rol);
    }

    /**
     * Actualiza un rol existente.
     *
     * @param idRol ID del rol a actualizar.
     * @param rol   El objeto Rol con los nuevos datos.
     * @return El rol actualizado, o null si no se encuentra.
     */
    @PutMapping("/{idRol}")
    public Rol updateRol(@PathVariable int idRol, @RequestBody Rol rol) {
        Optional<Rol> currentRol = rolRepository.findById(idRol);

        if (currentRol.isPresent()) {
            Rol updatedRol = currentRol.get();
            updatedRol.setNombre(rol.getNombre());
            return rolRepository.save(updatedRol);
        }

        return null;
    }

    /**
     * Elimina un rol por su ID.
     *
     * @param idRol ID del rol a eliminar.
     * @return El rol eliminado, o null si no se encuentra.
     */
    @DeleteMapping("/{idRol}")
    public Rol deleteRol(@PathVariable int idRol) {
        Optional<Rol> rol = rolRepository.findById(idRol);

        if (rol.isPresent()) {
            Rol deletedRol = rol.get();
            rolRepository.deleteById(idRol);
            return deletedRol;
        }

        return null;
    }
}

