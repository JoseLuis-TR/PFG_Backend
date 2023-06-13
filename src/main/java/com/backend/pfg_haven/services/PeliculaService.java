package com.backend.pfg_haven.services;

import com.backend.pfg_haven.controller.FicherosController;
import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.pelicula.PeliculaDTOConverter;
import com.backend.pfg_haven.dto.pelicula.PeliculaPostDTO;
import com.backend.pfg_haven.exception.MissingFilesException;
import com.backend.pfg_haven.fileupload.StorageService;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    private final StorageService storageService;

    /**
     * Se obtienen todas las películas del catálogo por página y ordenadas por nombre
     *
     * @return Lista de películas
     */
    public Map<String, Object> getAllPeliculas(int nPage){
        // Creamos el parámetro de búsqueda por la página que recibimos en la petición
        // TODO - Modificar el tamaño de la página
        Pageable parametrosPagina = PageRequest.of(nPage,30, Sort.by("nombre").ascending());
        Page<Pelicula> listaPeliculas = peliculaRepository.findAll(parametrosPagina);
        // Convertimos la lista de peliculas al correspondiente DTO
        PeliculaDTOConverter peliculaDTOConverter = new PeliculaDTOConverter();
        List<PeliculaCarteleraDTO> listaPeliculasDTO = listaPeliculas.getContent().stream()
                .map(peliculaDTOConverter::convertToCarteleraDTO)
                .collect(Collectors.toList());
        // Creamos el objeto de respuesta paginada
        Map<String, Object> paginatedResponse = new HashMap<>();
        paginatedResponse.put("peliculas", listaPeliculasDTO);
        paginatedResponse.put("currentPage", listaPeliculas.getNumber());
        paginatedResponse.put("totalItems", listaPeliculas.getTotalElements());
        paginatedResponse.put("totalPages", listaPeliculas.getTotalPages());
        return paginatedResponse;
    }

    /**
     * Se obtiene un listado de peliculas completo en formato mini con su id y nombre
     *
     * @return Lista de peliculas
     */
    public List<Pelicula> getAllPeliculasMini() {
        List<Pelicula> listaPeliculas = peliculaRepository.findAllByOrderByNombre();
        if(listaPeliculas.isEmpty()) {
            throw new ResourceNotFoundException("No hay películas en el catálogo");
        } else {
            return listaPeliculas;
        }
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
     * Obtenemos el top 10 de peliculas por votos
     *
     * @return Lista de peliculas
     */
    public List<Pelicula> getTop10Peliculas() {
        return peliculaRepository.findTop10ByOrderByVotosDesc();
    }

    /**
     * Se elimina una pelicula por su id
     *
     * @param id Id de la película
     * @return Película eliminada
     */
    public Pelicula deletePeliculaById(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encuentra le pelicula que quiere eliminar"));
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

        Pelicula peliculaExiste = peliculaRepository.findByNombre(peliculaToUpdate.getNombre());

        if(peliculaExiste != null && !peliculaExiste.getId().equals(idPeliculaToUpdate)) {
            throw new EntityExistsException("Ya existe una película con ese nombre");
        }

        String urlPoster = null;
        String urlCaptura = null;

        if(posterToUpdate != null && !posterToUpdate.isEmpty()) {
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

    	pelicula.setNombre(peliculaToUpdate.getNombre() != null ? peliculaToUpdate.getNombre() : pelicula.getNombre());
    	pelicula.setDirector(peliculaToUpdate.getDirector() != null ? peliculaToUpdate.getDirector() : pelicula.getDirector());
    	pelicula.setDuracion(peliculaToUpdate.getDuracion() != null ? peliculaToUpdate.getDuracion() : pelicula.getDuracion());
    	pelicula.setTrailer(peliculaToUpdate.getTrailer() != null ? peliculaToUpdate.getTrailer() : pelicula.getTrailer());
    	pelicula.setSinopsis(peliculaToUpdate.getSinopsis() != null ? peliculaToUpdate.getSinopsis() : pelicula.getSinopsis());
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

    /**
     * Se añade un nuevo voto a una pelicula
     *
     * @param idPelicula Id de la pelicula a la que se le añade el voto
     * @return Pelicula con el voto añadido
     */
    public Pelicula addVoto(Long idPelicula) {
        Pelicula pelicula = peliculaRepository
                .findById(idPelicula)
                .orElseThrow(() -> new ResourceNotFoundException("No se encuentra la pelicula a la que quiere añadir el voto"));

        pelicula.setVotos(pelicula.getVotos() + 1);
        peliculaRepository.save(pelicula);

        return pelicula;
    }
}
