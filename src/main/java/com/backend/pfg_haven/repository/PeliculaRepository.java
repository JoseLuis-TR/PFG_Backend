package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    /**
     * Obtenemos una pelicula por su id
     */
}
