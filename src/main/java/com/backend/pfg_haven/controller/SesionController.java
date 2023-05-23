package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.sala.SalaDTO;
import com.backend.pfg_haven.dto.sesion.SesionDTO;
import com.backend.pfg_haven.dto.sesion.SesionDTOConverter;
import com.backend.pfg_haven.dto.sesion.SesionPostDTO;
import com.backend.pfg_haven.model.Sesion;
import com.backend.pfg_haven.services.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SesionController {

    private final SesionService sesionService;

    /**
     * Obtenemos un Hashmap con las sesiones de hoy y la pelicula que se proyecta en cada una
     * La pelicula llega transformada a PeliculaCarteleraDTO para que no se muestren todos los datos
     *
     * @return Lista de las sesiones de hoy
     */
    @GetMapping("/sesiones/hoy")
    public List<SesionDTO> getTodaySessions() {
        HashMap<Sesion, HashMap<String, Object>> relacionSesionPelicula = sesionService.getTodaySessions();
        return convertSesionesDTO(relacionSesionPelicula);
    }

    /**
     * Obtenemos todas las sesiones a partir de hoy
     *
     * @return Lista de las sesiones a partir de hoy
     */
    @GetMapping("/sesiones/desdeHoy")
    public List<SesionDTO> getSessionsAfterToday() {
        HashMap<Sesion, HashMap<String, Object>> relacionSesionesDesdeHoyPelicula = sesionService.getSessionsAfterToday();
        return convertSesionesDTO(relacionSesionesDesdeHoyPelicula);
    }

    /**
     * Obtenemos todas las sesiones a partir de hoy de una pelicula en especifico
     *
     * @param idPelicula ID de la pelicula
     * @return Lista de las sesiones
     */
    @GetMapping("/sesiones/desdeHoy/pelicula")
    public List<SesionDTO> getSessionsAfterTodayByPelicula(@RequestParam Long idPelicula) {
        HashMap<Sesion, HashMap<String, Object>> relacionSesionesDesdeHoyPelicula = sesionService.getSessionsAfterToday();
        HashMap<Sesion, HashMap<String, Object>> sesionesDesdeHoyPelicula = relacionSesionesDesdeHoyPelicula
                                                                                    .entrySet()
                                                                                    .stream()
                                                                                    .filter(entry -> {
                                                                                        HashMap<String, Object> infoSesion = entry.getValue();
                                                                                        PeliculaCarteleraDTO peliculaCarteleraDTO = (PeliculaCarteleraDTO) infoSesion.get("pelicula");
                                                                                        return peliculaCarteleraDTO.getId().equals(idPelicula);
                                                                                    })
                                                                                    .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), HashMap::putAll);
        if(sesionesDesdeHoyPelicula.isEmpty()) throw new ResourceNotFoundException("No hay sesiones para la pelicula con id: " + idPelicula);
        return convertSesionesDTO(sesionesDesdeHoyPelicula);
    }

    /**
     * Obtenemos la lista de sesiones que enviemos convertida a su correspondiente DTO
     * @param relacionSesionPelicula Hashmap con la relacion de sesion y pelicula
     * @return Lista de sesiones convertidas a DTO
     */
    private List<SesionDTO> convertSesionesDTO(HashMap<Sesion, HashMap<String, Object>> relacionSesionPelicula) {
        SesionDTOConverter sesionConverter = new SesionDTOConverter();
        List<SesionDTO> sesionesDTO = new ArrayList<>();
        relacionSesionPelicula.forEach((sesion, infoSesion) -> {
            SesionDTO sesionTransformada = sesionConverter.convertToDTO(sesion);
            sesionTransformada.setPeliculaCartelera((PeliculaCarteleraDTO) infoSesion.get("pelicula"));
            sesionTransformada.setSalaSesion((SalaDTO) infoSesion.get("sala"));
            sesionesDTO.add(sesionTransformada);
        });
        return sesionesDTO;
    }

    /**
     * Obtenemos todos los asientos ya reservados para una sesión
     *
     * @param idSesion Id de la sesión
     * @return Lista de asientos reservados
     */
    @GetMapping("/sesiones/{idSesion}/asientos")
    public List<Long> getReservedSeatsBySesionId(Long idSesion) {
        return sesionService.getReservedSeatsBySesionId(idSesion);
    }

    /**
     * Creamos una nueva sesión
     *
     * @param newSesion Sesion a crear
     * @return Sesion creada
     */
    @PostMapping("/sesiones")
    public ResponseEntity<Boolean> createSesion(@RequestBody SesionPostDTO newSesion) {
        Boolean sesionesGuardadas = sesionService.createSesion(newSesion);

        // SI ha ocurrido un error al guardar, lanzamos un bad request
        if(!sesionesGuardadas) return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    /**
     * Eliminamos una sesión
     *
     * @param idSesion Id de la sesión
     * @return Sesion eliminada
     */
    @DeleteMapping("/sesiones")
    public Sesion deleteSesion(@RequestParam Long idSesion) {
        return sesionService.deleteSesion(idSesion);
    }

}
