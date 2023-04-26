package com.backend.pfg_haven.services;

import com.backend.pfg_haven.dto.pelicula.PeliculaPostDTO;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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

    /**
     * Se crea una nueva pelicula en la base de datos
     *
     * @param newPelicula Pelicula a añadir
     * @return Pelicula añadida
     */
    public Pelicula addPeliculaAPI(PeliculaPostDTO newPelicula) {
        Pelicula peliculaExiste = peliculaRepository.findByNombre(newPelicula.getNombre());
        if(peliculaExiste != null) {
            throw new EntityExistsException("Ya existe una película con ese nombre");
        }
        Pelicula peliculaCreada = new Pelicula();
        peliculaCreada.setNombre(newPelicula.getNombre());
        peliculaCreada.setDirector(newPelicula.getDirector());
        peliculaCreada.setDuracion(newPelicula.getDuracion());
        peliculaCreada.setTrailer(newPelicula.getTrailer());
        peliculaCreada.setPoster(newPelicula.getPoster());
        peliculaCreada.setCaptura(newPelicula.getCaptura());
        peliculaCreada.setSinopsis(newPelicula.getSinopsis());
        peliculaRepository.save(peliculaCreada);
        return peliculaCreada;
    }
}
