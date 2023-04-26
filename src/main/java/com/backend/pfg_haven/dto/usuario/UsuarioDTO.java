package com.backend.pfg_haven.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    private String nick;

    private String email;

    private String avatar;

    private String rol;
}
