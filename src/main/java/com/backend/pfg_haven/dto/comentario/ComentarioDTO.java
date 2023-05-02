package com.backend.pfg_haven.dto.comentario;

import com.backend.pfg_haven.dto.usuario.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {

    private Long id;

    private String texto;

    private String fecha;

    private UsuarioDTO usuario;
}
