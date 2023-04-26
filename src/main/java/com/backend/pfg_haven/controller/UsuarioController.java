package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.usuario.UsuarioDTO;
import com.backend.pfg_haven.dto.usuario.UsuarioDTOConverter;
import com.backend.pfg_haven.model.Usuario;
import com.backend.pfg_haven.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Devolvemos todos los usuarios
     */
    @GetMapping("/usuarios")
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        UsuarioDTOConverter converter = new UsuarioDTOConverter();
        List<UsuarioDTO> usuariosDTO = usuarios.stream().map(converter::convertToDTO).collect(Collectors.toList());
        return usuariosDTO;
    }

    /**
     * Devolvemos un usuario en base a su ID
     */
    @GetMapping("/usuario/{idUsuario}")
    public UsuarioDTO getUsuarioById(@PathVariable Long idUsuario) {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        UsuarioDTOConverter converter = new UsuarioDTOConverter();
        UsuarioDTO usuarioDTO = converter.convertToDTO(usuario);
        return usuarioDTO;
    }
}
