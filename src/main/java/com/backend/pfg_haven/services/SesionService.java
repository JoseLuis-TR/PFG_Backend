package com.backend.pfg_haven.services;

import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.pelicula.PeliculaDTOConverter;
import com.backend.pfg_haven.dto.sala.SalaDTO;
import com.backend.pfg_haven.dto.sala.SalaDTOConverter;
import com.backend.pfg_haven.model.Sesion;
import com.backend.pfg_haven.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class SesionService {

    @Autowired
    private SesionRepository sesionRepository;

    /**
     * Obtenemos todas las sesiones de hoy
     *
     * @return Lista de las sesiones de hoy
     */
    public HashMap<Sesion, HashMap<String, Object>> getTodaySessions() {
        List<Sesion> sesionesHoy = sesionRepository.findByFechaEquals(LocalDate.now());
        if(sesionesHoy.isEmpty()) {
            throw new ResourceNotFoundException("No hay sesiones hoy");
        } else {
            return convertToCarteleraDTO(sesionesHoy);
        }
    }

    /**
     * Obtenemos todas las sesiones a partir de hoy
     *
     * @return Lista de las sesiones a partir de hoy
     */
    public HashMap<Sesion, HashMap<String, Object>> getSessionsAfterToday() {
        List<Sesion> sesionesDesdeHoy = sesionRepository.findByFechaGreaterThanEqualOrderByFechaAsc(LocalDate.now());
        if(sesionesDesdeHoy.isEmpty()) {
            throw new ResourceNotFoundException("No hay sesiones a partir de hoy");
        } else {
            return convertToCarteleraDTO(sesionesDesdeHoy);
        }
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
}
