package com.backend.pfg_haven.services;

import com.backend.pfg_haven.controller.FicherosController;
import com.backend.pfg_haven.dto.usuario.UsuarioModDTO;
import com.backend.pfg_haven.fileupload.StorageService;
import com.backend.pfg_haven.model.Usuario;
import com.backend.pfg_haven.model.UsuarioRol;
import com.backend.pfg_haven.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private StorageService storageService;

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

    /**
     * Obtenemos un usuario en base a su nick
     *
     * @param nickBuscado Nick del usuario a buscar
     * @return true si existe, false si no
     */
    public Boolean getUsuarioByNick(String nickBuscado) {
        Usuario foundUser = usuarioRepository.findByNick(nickBuscado);
        return foundUser != null;
    }

    /**
     * Obtenemos un usuario en base a su email
     *
     * @param emailBuscado Email del usuario a buscar
     * @return True si existe, false si no
     */
    public Boolean getUsuarioByEmail(String emailBuscado) {
        Usuario foundUser = usuarioRepository.findByEmail(emailBuscado);
        return foundUser != null;
    }

    /**
     * Revisamos si el login es correcto
     *
     * @param email Email del usuario
     * @param clave Clave del usuario
     * @return Usuario si el login es correcto, error 404 si no
     */
    public Usuario checkLogin(String email, String clave) {
        Usuario foundUser = usuarioRepository.findByEmail(email);
        if(foundUser == null) {
            throw new ResourceNotFoundException("No se encontró el usuario con email: " + email);
        } else {
            if(!foundUser.getClave().equals(clave)) {
                throw new ResourceNotFoundException("La clave no es correcta");
            } else {
                return foundUser;
            }
        }
    }

    /**
     * Creamos nuevo usuario en la base de datos
     *
     * @param newUsuario Usuario a crear
     * @return Usuario creado
     */
    public Usuario createUsuario(UsuarioModDTO newUsuario) {
        Boolean usuarioExist = getUsuarioByNick(newUsuario.getNick());
        Boolean emailExist = getUsuarioByEmail(newUsuario.getEmail());
        if(usuarioExist || emailExist) {
            throw new EntityExistsException("El usuario o email ya existe");
        }
        Usuario usuarioCreado = new Usuario();
        usuarioCreado.setNick(newUsuario.getNick());
        usuarioCreado.setEmail(newUsuario.getEmail());
        usuarioCreado.setClave(newUsuario.getClave());
        usuarioCreado.setRol(UsuarioRol.USER.toString());
        return usuarioRepository.save(usuarioCreado);
    }

    /**
     * Actualizamos un usuario en la base de datos
     *
     * @param idUsuario Identificador del usuario a actualizar
     * @param updatedUser Detalle a actualizar
     * @param newAvatar Avatar a actualizar
     */
    public Usuario updateUsuario(Long idUsuario, UsuarioModDTO updatedUser, MultipartFile newAvatar){
        Usuario usuarioToUpdate = usuarioRepository
                                            .findById(idUsuario)
                                            .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con id: " + idUsuario));

        Usuario nickExist = null;
        Usuario emailExist = null;
        if(!updatedUser.getNick().isEmpty()){
            nickExist = usuarioRepository.findByNick(updatedUser.getNick());
        }
        if(!updatedUser.getEmail().isEmpty()){
            emailExist = usuarioRepository.findByEmail(updatedUser.getEmail());
        }
        if(nickExist != null && !nickExist.getId().equals(idUsuario)) {
            throw new EntityExistsException("El nick ya existe");
        }
        if(emailExist != null && !emailExist.getId().equals(idUsuario)) {
            throw new EntityExistsException("El email ya existe");
        }

        String urlAvatar = null;

        if(newAvatar != null && !newAvatar.isEmpty()) {
            String avatarSubido = storageService.store(newAvatar);
            urlAvatar = MvcUriComponentsBuilder
                            .fromMethodName(FicherosController.class, "serveFile", avatarSubido, null)
                            .build().toUriString();
        }

        if(!updatedUser.getNick().isEmpty() && !updatedUser.getNick().equals(usuarioToUpdate.getNick())) {
            usuarioToUpdate.setNick(updatedUser.getNick());
        }
        if(!updatedUser.getEmail().isEmpty() && !updatedUser.getEmail().equals(usuarioToUpdate.getEmail())) {
            usuarioToUpdate.setEmail(updatedUser.getEmail());
        }
        if(!updatedUser.getClave().isEmpty() && !updatedUser.getClave().equals(usuarioToUpdate.getClave())) {
            usuarioToUpdate.setClave(updatedUser.getClave());
        }
        if(urlAvatar != null){
            usuarioToUpdate.setAvatar(urlAvatar);
        }
        return usuarioRepository.save(usuarioToUpdate);
    }

}
