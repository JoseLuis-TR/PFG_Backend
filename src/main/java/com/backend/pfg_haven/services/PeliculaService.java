package com.backend.pfg_haven.services;

import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    /**
     * Se obtienen todas las películas del catálogo
     *
     * @return Lista de películas
     */
    public List<Pelicula> getAllPeliculas() {
        List<Pelicula> listaPeliculas = new ArrayList<>(peliculaRepository.findAll());
        return listaPeliculas;
    }

    /**
     * Se obtiene una película por su id
     *
     * @param id Id de la película
     * @return Película
     */
    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la película con id: " + id));
    }
}
