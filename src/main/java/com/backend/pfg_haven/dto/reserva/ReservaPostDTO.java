package com.backend.pfg_haven.dto.reserva;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservaPostDTO {

    private Long id_usuario;

    private Long id_sesion;

    private List<Long> asientos;
}
