package com.backend.pfg_haven.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Asiento")
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fila", nullable = false)
    private char fila;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name="id_sala")
    @JsonBackReference
    private Sala sala;
}
