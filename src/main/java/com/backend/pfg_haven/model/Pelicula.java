package com.backend.pfg_haven.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", length = 200, nullable = false, unique = true)
    private String nombre;

    @Column(name = "director", length = 100, nullable = false)
    private String director;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "trailer", length = 50)
    private String trailer;

    @Column(name = "poster", length = 255, nullable = false)
    private String poster;

    // TODO - Asegurarme de que no puede ser nulo
    @Column(name = "captura", length = 255)
    private String captura;

    @Column(name = "sinopsis", length = 750, nullable = false)
    private String sinopsis;

    @Column(name="votos")
    private int votos = 0;

    @JsonManagedReference
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    private List<Sesion> sesiones;

    @JsonManagedReference
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

}
