package com.backend.pfg_haven.services;

import com.backend.pfg_haven.dto.reserva.ReservaPostDTO;
import com.backend.pfg_haven.model.Asiento;
import com.backend.pfg_haven.model.Reserva;
import com.backend.pfg_haven.model.Sesion;
import com.backend.pfg_haven.model.Usuario;
import com.backend.pfg_haven.repository.ReservaRepository;
import com.backend.pfg_haven.repository.SesionRepository;
import com.backend.pfg_haven.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private ReservaTieneAsientoService reservaTieneAsientoService;

    @Autowired
    private UsuarioHaVistoPeliculaService usuarioHaVistoPeliculaService;

    /**
     * Añadimos una nueva reserva
     *
     * @param newReserva la reserva a añadir
     * @return la reserva añadida
     */
    public Reserva addReserva(ReservaPostDTO newReserva){

        Usuario usuarioExists = null;
        if(newReserva.getId_usuario() != null){
            usuarioExists = usuarioRepository.findById(newReserva.getId_usuario()).orElseThrow( () -> new ResourceNotFoundException("No existe el usuario especificado"));
        }
        Sesion sesionExists = sesionRepository.findById(newReserva.getId_sesion()).orElseThrow( () -> new ResourceNotFoundException("No existe la sesion especificada"));

        Reserva newReservaParaTabla = new Reserva();
        newReservaParaTabla.setUsuario(usuarioExists);
        newReservaParaTabla.setSesion(sesionExists);

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoMySQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatoMySQL);
        newReservaParaTabla.setFecha(fechaFormateada);
        Reserva reserva = reservaRepository.save(newReservaParaTabla);

        // Añadimos un registro de los asientos escogidos en la sesion
        reservaTieneAsientoService.addReservaTieneAsiento(reserva.getId(), newReserva.getAsientos());

        if(usuarioExists != null){
            // Añadimos un registro de que el usuario ha visto la pelicula
            usuarioHaVistoPeliculaService.addUsuarioHaVistoPelicula(usuarioExists, sesionExists, fechaFormateada);
        }
        return reserva;
    }
}