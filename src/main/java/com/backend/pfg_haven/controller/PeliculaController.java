package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.pelicula.PeliculaDTOConverter;
import com.backend.pfg_haven.dto.pelicula.PeliculaPostDTO;
import com.backend.pfg_haven.exception.MissingFilesException;
import com.backend.pfg_haven.fileupload.StorageService;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.services.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    private final StorageService storageService;

    /**
     * Obtenemos todas las peliculas
     *
     * @return Lista de peliculas en cartelera
     */
    @GetMapping("/peliculas")
    public List<PeliculaCarteleraDTO> getAllPeliculas() {
        List<Pelicula> listaPeliculas = peliculaService.getAllPeliculas();
        PeliculaDTOConverter peliculaDTOConverter = new PeliculaDTOConverter();
        List<PeliculaCarteleraDTO> listaPeliculasDTO = listaPeliculas.stream().map(peliculaDTOConverter::convertToCarteleraDTO).collect(Collectors.toList());
        return listaPeliculasDTO;
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
     * Se elimina una pelicula por su id
     *
     * @param idPelicula Id de la pelicula
     * @return Pelicula eliminada
     */
    @DeleteMapping("/peliculas/{idPelicula}")
    public Pelicula deletePeliculaById(@PathVariable Long idPelicula) {
        return peliculaService.deletePeliculaById(idPelicula);
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
     * Se añade una nueva pelicula pero en esta ocasión recibiendo dos imagenes para
     * el poster y la captura de la pelicula
     *
     * @param newPelicula
     * @param poster
     * @param captura
     * @return Pelicula añadida
     */
    @PostMapping(value = "/peliculas/archivos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pelicula addPelicula(@RequestPart("nuevaPelicula") PeliculaPostDTO newPelicula,
                                @RequestPart("poster") MultipartFile poster,
                                @RequestPart("captura") MultipartFile captura) {
        String urlPoster = null;
        String urlCaptura = null;

        if (!poster.isEmpty() && !captura.isEmpty()){
            String posterSubido = storageService.store(poster);
            urlPoster = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", posterSubido, null)
                    .build().toUriString();
            String capturaSubida = storageService.store(captura);
            urlCaptura = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", capturaSubida, null)
                    .build().toUriString();
        } else {
            throw new MissingFilesException("Se deben subir los dos archivos");
        }

        return peliculaService.addPeliculaWithFiles(newPelicula, urlPoster, urlCaptura);
    }
}
