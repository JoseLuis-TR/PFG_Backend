package com.backend.pfg_haven.dto.sesion;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class SesionPostDTO {

    private LocalDate fecha;

    private String horas;

    private Long id_pelicula;

    private Long id_sala;
}
