package com.backend.pfg_haven.services;

import com.backend.pfg_haven.model.Usuario;
import com.backend.pfg_haven.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Obtenemos todos los usuarios dentro del sistema
     *
     * @return Lista de usuarios
     */
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron usuarios");
        } else {
            return usuarios;
        }
    }

    /**
     * Obtenemos un usuario en base a su ID
     *
     * @param id Identificador del usuario a buscar
     * @return Usuario o error 404 si no encuentra el usuario
     */
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id: " + id));
    }
}
