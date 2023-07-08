package com.carboexco.modulo_usuario.controller;

import com.carboexco.modulo_usuario.entity.Usuario;
import com.carboexco.modulo_usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://192.168.1.135:*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtiene todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param idUsuario ID del usuario.
     * @return El usuario correspondiente al ID, o null si no se encuentra.
     */
    @GetMapping("/{idUsuario}")
    public Usuario getUsuarioById(@PathVariable int idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {
            return usuario.get();
        }

        return null;
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario El objeto Usuario a crear.
     * @return El usuario creado.
     */
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param idUsuario ID del usuario a actualizar.
     * @param usuario   El objeto Usuario con los nuevos datos.
     * @return El usuario actualizado, o null si no se encuentra.
     */
    @PutMapping("/{idUsuario}")
    public Usuario updateUsuario(@PathVariable int idUsuario, @RequestBody Usuario usuario) {
        Optional<Usuario> currentUsuario = usuarioRepository.findById(idUsuario);

        if (currentUsuario.isPresent()) {
            Usuario updatedUsuario = currentUsuario.get();
            updatedUsuario.setNombre(usuario.getNombre());
            updatedUsuario.setContracenia(usuario.getContracenia());
            updatedUsuario.setCodigoRadio(usuario.getCodigoRadio());
            return usuarioRepository.save(updatedUsuario);
        }

        return null;
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param idUsuario ID del usuario a eliminar.
     * @return El usuario eliminado, o null si no se encuentra.
     */
    @DeleteMapping("/{idUsuario}")
    public Usuario deleteUsuario(@PathVariable int idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()) {
            Usuario deletedUsuario = usuario.get();
            usuarioRepository.deleteById(idUsuario);
            return deletedUsuario;
        }

        return null;
    }
}

