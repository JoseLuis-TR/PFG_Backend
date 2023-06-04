package com.backend.pfg_haven.services;

import com.backend.pfg_haven.dto.comentario.ComentarioDTO;
import com.backend.pfg_haven.dto.comentario.ComentarioDTOConverter;
import com.backend.pfg_haven.dto.comentario.ComentarioPostDTO;
import com.backend.pfg_haven.model.Comentario;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.model.Usuario;
import com.backend.pfg_haven.repository.ComentarioRepository;
import com.backend.pfg_haven.repository.PeliculaRepository;
import com.backend.pfg_haven.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    /**
     * Obtenemos todos los comentarios de una pelicula paginados ordenados por fecha descendente
     * @param idPelicula id de la pelicula
     * @param nPage numero de pagina
     * @return lista de comentarios paginados
     */
    public Map<String, Object> getComentariosPelicula(Long idPelicula, int nPage) {
        Pageable datosPagina = PageRequest.of(nPage, 100, Sort.by("fecha").descending());
        Page<Comentario> listaComentariosPagina = comentarioRepository.findAllByPeliculaId(idPelicula, datosPagina);
        // Convertimos la lista de comentarios al correspondiente DTO
        ComentarioDTOConverter comentarioDTOConverter = new ComentarioDTOConverter();
        List<ComentarioDTO> listaComentariosDTO = listaComentariosPagina.getContent()
                                                                        .stream()
                                                                        .map(comentarioDTOConverter::convertToDTO)
                                                                        .collect(Collectors.toList());
        Map<String, Object> paginatedResponse = new HashMap<>();
        paginatedResponse.put("comentarios", listaComentariosDTO);
        paginatedResponse.put("currentPage", listaComentariosPagina.getNumber());
        paginatedResponse.put("totalItems", listaComentariosPagina.getTotalElements());
        paginatedResponse.put("totalPages", listaComentariosPagina.getTotalPages());

        return paginatedResponse;
    }

    /**
     * Añadimos un nuevo comentario a una pelicula
     * @param newComentario comentario a añadir
     * @return comentario añadido
     */
    public Comentario addComentario(ComentarioPostDTO newComentario) {
        System.out.println(newComentario.getId_usuario());
        Optional<Usuario> usuariosExists = usuarioRepository.findById(newComentario.getId_usuario());
        Optional<Pelicula> peliculaExists = peliculaRepository.findById(newComentario.getId_pelicula());
        if(usuariosExists.isEmpty() || peliculaExists.isEmpty()) {
            throw new ResourceNotFoundException("El usuario o la pelicula no existen");
        }

        Comentario comentario = new Comentario();
        comentario.setUsuario(usuariosExists.get());
        comentario.setPelicula(peliculaExists.get());
        comentario.setTexto(newComentario.getTexto());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        comentario.setFecha(formattedNow);
        return comentarioRepository.save(comentario);
    }

    /**
     * Eliminamos un comentario
     * @param idComentario id del comentario
     * @return comentario eliminado
     */
    public Comentario deleteComentario(Long idComentario) {
        Optional<Comentario> comentarioExists = comentarioRepository.findById(idComentario);
        if(comentarioExists.isEmpty()) {
            throw new ResourceNotFoundException("El comentario no existe");
        }
        comentarioRepository.deleteById(idComentario);
        return comentarioExists.get();
    }
}
