package com.backend.pfg_haven.dto.pelicula;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaCarteleraDTO {

    private Long id;

    private String nombre;

    private String director;

    private Integer duracion;

    private String trailer;

    private String poster;

    private String captura;

    private String sinopsis;

    private int votos;
}
