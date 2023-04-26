package com.backend.pfg_haven.dto.asiento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsientoDTO {

    private Long id;

    private char fila;

    private Integer numero;

    private Long id_sala;

}
