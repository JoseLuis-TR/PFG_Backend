package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.pelicula.PeliculaDTOConverter;
import com.backend.pfg_haven.dto.pelicula.PeliculaPostDTO;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.services.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    /**
     * Obtenemos lista de todas las peliculas
     *
     * @return Lista de peliculas en cartelera
     */
    @GetMapping("/peliculas/page")
    public Map<String, Object> getAllPeliculas(@RequestParam int numberPage) {
        return peliculaService.getAllPeliculas(numberPage);
    }

    /**
     * Obtenemos una pelicula por su ID
     *
     * @param idPelicula Identificador de la pelicula
     * @return Pelicula o error 404 si no encuentra la pelicula
     */
    @GetMapping("/peliculas")
    public PeliculaCarteleraDTO getPeliculaById(@RequestParam Long idPelicula) {
        Pelicula peliculaEncontrada = peliculaService.getPeliculaById(idPelicula);
        PeliculaDTOConverter peliculaDTOConverter = new PeliculaDTOConverter();
        return peliculaDTOConverter.convertToCarteleraDTO(peliculaEncontrada);
    }

    /**
     * Se elimina una pelicula por su id
     *
     * @param idPelicula Id de la pelicula
     * @return Pelicula eliminada
     */
    @DeleteMapping("/peliculas")
    public Pelicula deletePeliculaById(@RequestParam Long idPelicula) {
        return peliculaService.deletePeliculaById(idPelicula);
    }

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
     * Se añade una nueva pelicula pero en esta ocasión recibiendo dos imagenes para
     * el poster y la captura de la pelicula
     *
     * @param newPelicula Datos de la pelicula
     * @param poster Poster
     * @param captura Captura
     * @return Pelicula añadida
     */
    @PostMapping(value = "/peliculas/archivos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pelicula addPelicula(@RequestPart("nuevaPelicula") PeliculaPostDTO newPelicula,
                                @RequestPart("poster") MultipartFile poster,
                                @RequestPart("captura") MultipartFile captura) {
        return peliculaService.addPeliculaWithFiles(newPelicula, poster, captura);
    }

    /**
     * Se actualiza una pelicula por su id
     *
     * @param peliculaToUpdate Id de la pelicula a actualizar
     * @param peliculaToUpdate Nuevos datos de la pelicula
     * @param posterToUpdate Poster de la pelicula
     * @param capturaToUpdate Captura de la pelicula
     * @return Pelicula actualizada
     */
    @PutMapping(value = "/peliculas", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pelicula updatePelicula(@RequestParam Long idPelicula,
                                   @RequestPart("peliculaToUpdate") PeliculaPostDTO peliculaToUpdate,
                                   @RequestPart(value = "posterToUpdate", required = false) MultipartFile posterToUpdate,
                                   @RequestPart(value = "capturaToUpdate", required = false) MultipartFile capturaToUpdate) {
        return peliculaService.updatePelicula(idPelicula, peliculaToUpdate, posterToUpdate, capturaToUpdate);
    }
}
