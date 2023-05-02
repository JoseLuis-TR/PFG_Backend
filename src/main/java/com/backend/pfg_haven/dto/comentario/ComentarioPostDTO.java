package com.backend.pfg_haven.dto.comentario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioPostDTO {

    private Long id_usuario;

    private Long id_pelicula;

    private String texto;

}
