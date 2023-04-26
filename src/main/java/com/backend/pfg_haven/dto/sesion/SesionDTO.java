package com.backend.pfg_haven.dto.sesion;

import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.sala.SalaDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SesionDTO {

    private Long id;

    private LocalDate fecha;

    private String horas;

    private SalaDTO salaSesion;

    private PeliculaCarteleraDTO peliculaCartelera;
}
