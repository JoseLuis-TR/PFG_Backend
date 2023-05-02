package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query("SELECT c FROM Comentario c WHERE c.pelicula.id = :idPelicula")
    Page<Comentario> findAllByPeliculaId(Long idPelicula, Pageable pageable);
}
