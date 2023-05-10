package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.comentario.ComentarioPostDTO;
import com.backend.pfg_haven.model.Comentario;
import com.backend.pfg_haven.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;


    /**
     * Obtenemos todos los comentarios de una película paginados
     */
    @GetMapping("/comentarios/pelicula/{idPelicula}/paginado/{nPage}")
    public Map<String, Object> getComentariosPelicula(@PathVariable Long idPelicula, @PathVariable int nPage) {
        System.out.println("ComentarioController.getComentariosPelicula");
        return comentarioService.getComentariosPelicula(idPelicula, nPage);
    }

    /**
     * Eliminamos un comentario usando su id
     *
     * @param idComentario id del comentario a eliminar
     * @return comentario eliminado
     */
    @DeleteMapping("/comentarios/{idComentario}")
    public Comentario deleteComentario(@PathVariable Long idComentario) {
        return comentarioService.deleteComentario(idComentario);
    }

    /**
     * Añadimos un nuevo comentario
     *
     * @param newComentario comentario a añadir
     * @return comentario añadido
     */
    @PostMapping("/comentarios")
    public Comentario addComentario(@RequestBody ComentarioPostDTO newComentario) {
        return comentarioService.addComentario(newComentario);
    }
}
