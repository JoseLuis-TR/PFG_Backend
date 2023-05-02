package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.pelicula.PeliculaCarteleraDTO;
import com.backend.pfg_haven.dto.sala.SalaDTO;
import com.backend.pfg_haven.dto.sesion.SesionDTO;
import com.backend.pfg_haven.dto.sesion.SesionDTOConverter;
import com.backend.pfg_haven.model.Sesion;
import com.backend.pfg_haven.services.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
