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
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nick", length = 30, nullable = false, unique = true)
    private String nick;

    @Column(name = "email", length = 85, nullable = false, unique = true)
    private String email;

    @Column(name = "clave", length = 255, nullable = false)
    private String clave;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "rol", length = 45, nullable = false)
    private String rol;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas;
}
