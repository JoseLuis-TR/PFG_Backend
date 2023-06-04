package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.usuario.UsuarioDTO;
import com.backend.pfg_haven.dto.usuario.UsuarioDTOConverter;
import com.backend.pfg_haven.dto.usuario.UsuarioModDTO;
import com.backend.pfg_haven.dto.usuario.UsuarioRequestDTO;
import com.backend.pfg_haven.model.Usuario;
import com.backend.pfg_haven.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/usuario")
    public UsuarioDTO getUsuarioById(@RequestParam Long idUsuario) {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        UsuarioDTOConverter converter = new UsuarioDTOConverter();
        return converter.convertToDTO(usuario);
    }

    /**
     * Buscamos usuario por su nick
     *
     * @param nickBuscado
     * @return true si existe, false si no existe
     */
    @GetMapping("/usuario/nick")
    public Boolean getUsuarioByNick(@RequestParam String nickBuscado) {
        Boolean usuarioExist = usuarioService.getUsuarioByNick(nickBuscado);
        return usuarioExist;
    }

    /**
     * Buscamos usuario por su email
     *
     * @param emailBuscado
     * @return true si existe, false si no existe
     */
    @GetMapping("/usuario/email")
    public Boolean getUsuarioByEmail(@RequestParam String emailBuscado) {
        Boolean usuarioExist = usuarioService.getUsuarioByEmail(emailBuscado);
        return usuarioExist;
    }

    @PostMapping("/usuario/login")
    public UsuarioDTO loginUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        String email = usuarioRequestDTO.getEmail();
        String clave = usuarioRequestDTO.getClave();
    	Usuario usuario = usuarioService.checkLogin(email, clave);
    	UsuarioDTOConverter converter = new UsuarioDTOConverter();
    	return converter.convertToDTO(usuario);
    }

    /**
     * Creamos un nuevo usuario
     *
     * @param newUsuario
     * @return UsuarioDTO
     */
    @PostMapping("/usuario")
    public UsuarioDTO createUsuario(@RequestBody UsuarioModDTO newUsuario) {
        Usuario usuario = usuarioService.createUsuario(newUsuario);
        UsuarioDTOConverter converter = new UsuarioDTOConverter();
        return converter.convertToDTO(usuario);
    }

    /**
     * Modificamos un usuario
     *
     * @param idUsuario Usuario que queremos modificar
     * @param userToUpdate Datos a modificar (nick, email, clave)
     * @param newAvatar Nuevo avatar a subir
     * @return UsuarioDTO
     */
    @PutMapping(value = "/usuario", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UsuarioDTO updateUsuario(@RequestParam Long idUsuario,
                                    @RequestPart("userToUpdate") UsuarioModDTO userToUpdate,
                                    @RequestPart(value ="newAvatar", required = false) MultipartFile newAvatar) {
        Usuario usuario = usuarioService.updateUsuario(idUsuario, userToUpdate, newAvatar);
        UsuarioDTOConverter converter = new UsuarioDTOConverter();
        return converter.convertToDTO(usuario);
    }
}
