package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.repository.PeliculaRepository;
import com.backend.pfg_haven.services.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    /**
     * Obtenemos todas las peliculas
     *
     * @return Lista de peliculas en cartelera
     */
    @GetMapping("/peliculas")
    public List<Pelicula> getAllPeliculas() {
        return peliculaService.getAllPeliculas();
    }

    /**
     * Obtenemos una pelicula por su ID
     *
     * @param id Identificador de la pelicula
     * @return Pelicula o error 404 si no encuentra la pelicula
     */
    @GetMapping("/peliculas/{idPelicula}")
    public Pelicula getPeliculaById(@PathVariable Long idPelicula) {
        return peliculaService.getPeliculaById(idPelicula);
    }

    /**
     * Añadimos una nueva nueva pelicula recibiendo datos de un formulario
     * a los que se le añade una imagen y se guarda en la base de datos utilizando
     * el metodo POST
     */
    @PostMapping(value = "/peliculas", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)


}
