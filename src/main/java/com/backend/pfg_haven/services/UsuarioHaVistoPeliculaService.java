package com.backend.pfg_haven.services;

import com.backend.pfg_haven.model.Sesion;
import com.backend.pfg_haven.model.Usuario;
import com.backend.pfg_haven.model.UsuarioHaVistoPelicula;
import com.backend.pfg_haven.repository.UsuarioHaVistoPeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioHaVistoPeliculaService {

    @Autowired
    private UsuarioHaVistoPeliculaRepository usuarioHaVistoPeliculaRepository;

    /**
     * Añadimos la pelicula que ha visto junto al usuario en la tabla UsuarioHaVistoPelicula
     *
     * @param usuario el usuario que ha visto la pelicula
     * @param sesion la sesion en la que se ha visto la pelicula
     * @param fechaHoy la fecha de hoy
     */
    public void addUsuarioHaVistoPelicula(Usuario usuario, Sesion sesion, String fechaHoy) {
        UsuarioHaVistoPelicula usuarioVePelicula = new UsuarioHaVistoPelicula();
        usuarioVePelicula.setUsuario(usuario);
        usuarioVePelicula.setPelicula(sesion.getPelicula());
        usuarioVePelicula.setFecha(fechaHoy);
        System.out.println(usuarioVePelicula.getId());
        usuarioHaVistoPeliculaRepository.save(usuarioVePelicula);
    }

}
