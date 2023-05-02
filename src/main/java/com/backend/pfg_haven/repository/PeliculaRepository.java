package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Pelicula findByNombre(String titulo);

    Page<Pelicula> findAll(Pageable pageable);
}
