package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findAllByOrderByFechaDesc();

    /**
     * Obtenemos los últimos diez comentarios
     *
     * @return Lista de comentarios
     */
    List<Comentario> findTop10ByOrderByFechaDesc();

    /**
     * Obtenemos los comentarios de una película en concreto
     *
     * @param idPelicula
     * @return Lista de comentarios
     */
    List<Comentario> findAllByPeliculaOrderByFechaDesc(Long idPelicula);

    /**
     * Obtenemos los comentarios de un usuario en concreto
     *
     * @param idUsuario
     * @return Lista de comentarios
     */
    List<Comentario> findAllByUsuarioOrderByFechaDesc(Long idUsuario);
}
