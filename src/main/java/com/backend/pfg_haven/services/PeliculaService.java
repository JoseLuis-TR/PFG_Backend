package com.backend.pfg_haven.services;

import com.backend.pfg_haven.controller.FicherosController;
import com.backend.pfg_haven.dto.pelicula.PeliculaPostDTO;
import com.backend.pfg_haven.exception.MissingFilesException;
import com.backend.pfg_haven.fileupload.StorageService;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    private final StorageService storageService;

    /**
     * Se obtienen todas las películas del catálogo
     *
     * @return Lista de películas
     */
    public List<Pelicula> getAllPeliculas() {
        return new ArrayList<>(peliculaRepository.findAll());
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
     * Se elimina una pelicula por su id
     *
     * @param id Id de la película
     * @return Película eliminada
     */
    public Pelicula deletePeliculaById(Long id) {
        System.out.println("Id de la pelicula a eliminar: " + id);
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encuentra le pelicula que quiere eliminar"));
        System.out.println("Pelicula a eliminar: " + pelicula);
        peliculaRepository.deleteById(id);
        return pelicula;
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

    /**
     * Se crea una nueva pelicula en la base de datos
     * recibiendo un formulario y dos archivos
     *
     * @param newPelicula Pelicula a añadir
     * @param poster Poster de la pelicula
     * @param captura Captura de la pelicula
     * @return Pelicula añadida
     */
    public Pelicula addPeliculaWithFiles(PeliculaPostDTO newPelicula, MultipartFile poster, MultipartFile captura) {

        Pelicula peliculaExiste = peliculaRepository.findByNombre(newPelicula.getNombre());
        if(peliculaExiste != null) {
            throw new EntityExistsException("Ya existe una película con ese nombre");
        }

        String urlPoster;
        String urlCaptura;

        if (!poster.isEmpty() && !captura.isEmpty()){
            String posterSubido = storageService.store(poster);
            urlPoster = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", posterSubido, null)
                    .build()
                    .toUriString();
            String capturaSubida = storageService.store(captura);
            urlCaptura = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", capturaSubida, null)
                    .build()
                    .toUriString();
        } else {
            throw new MissingFilesException("Se deben subir los dos archivos");
        }

        Pelicula peliculaCreada = new Pelicula();
        peliculaCreada.setNombre(newPelicula.getNombre());
        peliculaCreada.setDirector(newPelicula.getDirector());
        peliculaCreada.setDuracion(newPelicula.getDuracion());
        peliculaCreada.setTrailer(newPelicula.getTrailer());
        peliculaCreada.setPoster(urlPoster);
        peliculaCreada.setCaptura(urlCaptura);
        peliculaCreada.setSinopsis(newPelicula.getSinopsis());
        peliculaRepository.save(peliculaCreada);
        return peliculaCreada;
    }

    /**
     * Se actualiza una pelicula en la base de datos
     * @param idPeliculaToUpdate Id de la pelicula a actualizar
     * @param peliculaToUpdate Datos de la pelicula a actualizar
     * @param posterToUpdate Poster de la pelicula
     * @param capturaToUpdate Captura de la pelicula
     * @return Pelicula actualizada
     */
    public Pelicula updatePelicula(Long idPeliculaToUpdate, PeliculaPostDTO peliculaToUpdate, MultipartFile posterToUpdate, MultipartFile capturaToUpdate) {
    	Pelicula pelicula = peliculaRepository
                                .findById(idPeliculaToUpdate)
                                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra le pelicula que quiere actualizar"));

        String urlPoster = null;
        String urlCaptura = null;

        System.out.println("Poster a actualizar: " + posterToUpdate);
        if(posterToUpdate != null && !posterToUpdate.isEmpty()) {
            System.out.println("Poster a actualizar: " + posterToUpdate);
            String posterSubido = storageService.store(posterToUpdate);
            urlPoster = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", posterSubido, null)
                    .build().toUriString();
        }
        if(capturaToUpdate != null && !capturaToUpdate.isEmpty()) {
            String capturaSubida = storageService.store(capturaToUpdate);
            urlCaptura = MvcUriComponentsBuilder
                    .fromMethodName(FicherosController.class, "serveFile", capturaSubida, null)
                    .build().toUriString();
        }

    	pelicula.setNombre(peliculaToUpdate.getNombre());
    	pelicula.setDirector(peliculaToUpdate.getDirector());
    	pelicula.setDuracion(peliculaToUpdate.getDuracion());
    	pelicula.setTrailer(peliculaToUpdate.getTrailer());
    	pelicula.setSinopsis(peliculaToUpdate.getSinopsis());
        // Comprobamos si se ha subido un nuevo poster o captura
        // y en caso de ser así, comprobamos si el poster o la captura
        // esta alojado en el servidor, en caso de estarlo lo borramos
        // y actualizamos la url del poster o captura
        if(urlPoster != null){
            if(pelicula.getPoster().contains("/files/")){
                String posterFilename = pelicula.getPoster().substring(pelicula.getPoster().lastIndexOf("/files/") + 1);
                storageService.delete(posterFilename);
                pelicula.setPoster(urlPoster);
            } else {
                pelicula.setPoster(urlPoster);
            }
        }
        if(urlCaptura != null){
            if(pelicula.getCaptura().contains("/files/")){
                String capturaFilename = pelicula.getCaptura().substring(pelicula.getCaptura().lastIndexOf("/files/") + 1);
                storageService.delete(capturaFilename);
                pelicula.setCaptura(urlCaptura);
            } else {
                pelicula.setCaptura(urlCaptura);
            }
        }
    	peliculaRepository.save(pelicula);

    	return pelicula;
    }
}
