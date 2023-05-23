package com.backend.pfg_haven.services;

import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.pelicula.PeliculaDTOConverter;
import com.backend.pfg_haven.dto.sala.SalaDTO;
import com.backend.pfg_haven.dto.sala.SalaDTOConverter;
import com.backend.pfg_haven.dto.sesion.SesionPostDTO;
import com.backend.pfg_haven.model.*;
import com.backend.pfg_haven.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ReservaTieneAsientoRepository reservaTieneAsientoRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private SalaRepository salaRepository;

    /**
     * Obtenemos todas las sesiones de hoy
     *
     * @return Lista de las sesiones de hoy
     */
    public HashMap<Sesion, HashMap<String, Object>> getTodaySessions() {
        List<Sesion> sesionesHoy = sesionRepository.findByFechaEquals(LocalDate.now());
        if(sesionesHoy.isEmpty()) {
            throw new ResourceNotFoundException("No hay sesiones hoy");
        }
        return convertToCarteleraDTO(sesionesHoy);
    }

    /**
     * Obtenemos todas las sesiones a partir de hoy
     *
     * @return Lista de las sesiones a partir de hoy
     */
    public HashMap<Sesion, HashMap<String, Object>> getSessionsAfterToday() {
        List<Sesion> sesionesDesdeHoy = sesionRepository.findByFechaGreaterThanEqualOrderByFecha(LocalDate.now());
        if(sesionesDesdeHoy.isEmpty()) {
            throw new ResourceNotFoundException("No hay sesiones a partir de hoy");
        }
        return convertToCarteleraDTO(sesionesDesdeHoy);
    }

    /**
     * Crea un hashmap con una relacion sesion-pelicula donde pelicula se devuelve como PeliculaCarteleraDTO
     * para evitar mostrar toda la info no necesaria
     * @param sesiones Lista de sesiones
     * @return HashMap con la relacion sesion-pelicula
     */
    private HashMap<Sesion, HashMap<String, Object>> convertToCarteleraDTO(List<Sesion> sesiones) {
        HashMap<Sesion, HashMap<String, Object>> relacionSesionPelicula = new HashMap<>();
        PeliculaDTOConverter peliculaConverter = new PeliculaDTOConverter();
        SalaDTOConverter salaConverter = new SalaDTOConverter();
        sesiones.forEach((sesion) -> {
            PeliculaCarteleraDTO peliculaCartelera = peliculaConverter.convertToCarteleraDTO(sesion.getPelicula());
            SalaDTO infoSala = salaConverter.convertToDTO(sesion.getSala());
            HashMap<String, Object> infoPeliculaSala = new HashMap<>() {{
                put("pelicula", peliculaCartelera);
                put("sala", infoSala);
            }};
            relacionSesionPelicula.put(sesion, infoPeliculaSala);
        });
        return relacionSesionPelicula;
    }

    /**
     * Obtenemos una lista de asientos reservados para una sesión
     *
     * @param idSesion
     * @return Lista de asientos reservados
     */
    public List<Long> getReservedSeatsBySesionId(Long idSesion){
        List<Reserva> reservas = reservaRepository.findBySesionId(idSesion);
        if(reservas.isEmpty()) throw new ResourceNotFoundException("No hay reservas para esta sesión");

        List<Long> idAsientosReservados = new ArrayList<>();
        for(Reserva reserva : reservas){
            List<ReservaTieneAsiento> asientosEnReserva = reservaTieneAsientoRepository.findAllByReservaId(reserva.getId());

            asientosEnReserva.forEach((asiento) -> {
                idAsientosReservados.add(asiento.getAsiento().getId());
            });
        }
        return idAsientosReservados;
    }

    /**
     * Creamos una nueva sesión
     *
     * @param newSesion
     * @return Sesion creada
     */
    public Boolean createSesion(SesionPostDTO newSesion) {
        Pelicula peliculaExists = peliculaRepository.findById(newSesion.getId_pelicula())
                                                        .orElseThrow( () -> new ResourceNotFoundException("No existe la pelicula con id: " + newSesion.getId_pelicula()));
        Sala salaExists = salaRepository.findById(newSesion.getId_sala())
                                                        .orElseThrow( () -> new ResourceNotFoundException("No existe la sala con id: " + newSesion.getId_sala()));

        String[] horasArray = newSesion.getHoras().split(",");

        for(String hora : horasArray){
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            try {
                java.util.Date horaParseada = dateFormat.parse(hora);
                Time horaFormatoTime = new Time(horaParseada.getTime());
                Sesion createdSesion = new Sesion();
                createdSesion.setFecha(newSesion.getFecha());
                createdSesion.setHora(horaFormatoTime);
                createdSesion.setPelicula(peliculaExists);
                createdSesion.setSala(salaExists);
                sesionRepository.save(createdSesion);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Eliminamos una sesión
     *
     * @param idSesion
     * @return Sesion eliminada
     */
    public Sesion deleteSesion(Long idSesion) {
        Sesion sesionExists = sesionRepository.findById(idSesion)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la sesión con id: " + idSesion));
        sesionRepository.delete(sesionExists);
        return sesionExists;
    }
}
