package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.pelicula.PeliculaPostDTO;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.services.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    //@PostMapping(value = "/peliculas", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    /**
     * Se añade una nueva pelicula a la base de datos recibiendo los datos
     * que se recogen de la llamada a la API de TMDB
     * Devuelve el codigo HTTP de creacion y la pelicula
     */
    @PostMapping("/peliculas")
    public Pelicula addPeliculaAPI(@RequestBody PeliculaPostDTO newPelicula){
        return peliculaService.addPeliculaAPI(newPelicula);
    }

    /**
     * Se elimina una pelicula por su id
     *
     * @param idPelicula Id de la pelicula
     * @return Pelicula eliminada
     */
    @DeleteMapping("/peliculas/{idPelicula}")
    public Pelicula deletePeliculaById(@PathVariable Long idPelicula) {
        return peliculaService.deletePeliculaById(idPelicula);
    }
}
