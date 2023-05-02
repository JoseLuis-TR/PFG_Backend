package com.backend.pfg_haven.services;

import com.backend.pfg_haven.dto.reserva.ReservaPostDTO;
import com.backend.pfg_haven.model.*;
import com.backend.pfg_haven.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SesionRepository sesionRepository;
    @Autowired
    private ReservaTieneAsientoRepository reservaTieneAsientoRepository;

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private UsuarioHaVistoPeliculaRepository usuarioHaVistoPeliculaRepository;

    /**
     * Añadimos una nueva reserva
     *
     * @param newReserva la reserva a añadir
     * @return la reserva añadida
     */
    public Reserva addReserva(ReservaPostDTO newReserva){
        Usuario usuarioExists = usuarioRepository.findById(newReserva.getId_usuario()).orElseThrow( () -> new ResourceNotFoundException("No existe el usuario especificado"));
        Sesion sesionExists = sesionRepository.findById(newReserva.getId_sesion()).orElseThrow( () -> new ResourceNotFoundException("No existe la sesion especificada"));

        Reserva newReservaParaTabla = new Reserva();
        newReservaParaTabla.setUsuario(usuarioExists);
        newReservaParaTabla.setSesion(sesionExists);

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoMySQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatoMySQL);
        newReservaParaTabla.setFecha(fechaFormateada);
        Reserva reserva = reservaRepository.save(newReservaParaTabla);

        // TODO - Mover estas a sus servicios correspondientes
        // Añadimos un registro de los asientos escogidos en la sesion
        addReservaTieneAsiento(reserva.getId(), newReserva.getAsientos());
        // Añadimos un registro de que el usuario ha visto la pelicula
        addUsuarioHaVistoPelicula(usuarioExists, sesionExists, fechaFormateada);
        return reserva;
    }

    /**
     * Añadimos la reserva junto a los ids de los asientos a la tabla reserva_tiene_asiento
     *
     * @param idReserva el id de la reserva
     * @param listaAsientos la lista de ids de los asientos
     */
    public void addReservaTieneAsiento(Long idReserva, List<Long> listaAsientos){
        Reserva reservaExists = reservaRepository.findById(idReserva).orElseThrow( () -> new ResourceNotFoundException("No existe la reserva especificada"));
        for(Long idAsiento : listaAsientos){
            Asiento asientoExists = asientoRepository.findById(idAsiento).orElseThrow( () -> new ResourceNotFoundException("No existe el asiento especificado"));

            ReservaTieneAsiento reservaTieneAsiento = new ReservaTieneAsiento();
            reservaTieneAsiento.setAsiento(asientoExists);
            reservaTieneAsiento.setReserva(reservaExists);
            reservaTieneAsientoRepository.save(reservaTieneAsiento);
        }
    }

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
        usuarioHaVistoPeliculaRepository.save(usuarioVePelicula);
    }
}