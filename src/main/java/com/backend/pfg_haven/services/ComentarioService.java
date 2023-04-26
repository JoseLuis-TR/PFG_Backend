package com.backend.pfg_haven.services;

import com.backend.pfg_haven.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    /**
     * Obtenemos los últimos diez comentarios
     *
     * @return Lista de comentarios
     */
    public Object getLast10Comentarios() {
        return comentarioRepository.findTop10ByOrderByFechaDesc();
    }

    /**
     * Obtenemos los comentarios de una película en concreto
     *
     * @param idPelicula
     * @return Lista de comentarios
     */
    public Object getComentariosByIdPelicula(Long idPelicula) {
        return comentarioRepository.findAllByPeliculaOrderByFechaDesc(idPelicula);
    }
    /**
     * Obtenemos los comentarios de un usuario en concreto
     *
     * @param idUsuario
     * @return Lista de comentarios
     */
    public Object getComentariosByIdUsuario(Long idUsuario) {
        return comentarioRepository.findAllByUsuarioOrderByFechaDesc(idUsuario);
    }
}
