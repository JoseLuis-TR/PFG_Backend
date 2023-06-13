package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Pelicula findByNombre(String titulo);

    Page<Pelicula> findAll(Pageable pageable);

    List<Pelicula> findAllByOrderByNombre();

    // Se añade una query para descartar las pelis con 0 votos
    @Query("SELECT p FROM Pelicula p WHERE p.votos > 0 ORDER BY p.votos DESC")
    List<Pelicula> findTop10ByOrderByVotosDesc();
}
