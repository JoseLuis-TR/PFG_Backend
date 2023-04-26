package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.model.Comentario;
import com.backend.pfg_haven.repository.ComentarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ComentarioController {

    ComentarioRepository comentarioRepository;

    /**
     * Obtenemos los comentarios más recientes de toda la web
     *
     * @return lista de comentarios
     */
    @GetMapping("/comentarios/recientes")
    public List<Comentario> getComentariosRecientes() {
        return comentarioRepository.findAllByOrderByFechaDesc();
    }
}
