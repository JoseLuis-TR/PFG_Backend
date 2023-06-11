package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.pelicula.PeliculaDTOConverter;
import com.backend.pfg_haven.dto.sala.SalaDTO;
import com.backend.pfg_haven.dto.sala.SalaDTOConverter;
import com.backend.pfg_haven.model.Pelicula;
import com.backend.pfg_haven.model.Sala;
import com.backend.pfg_haven.services.SalaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @GetMapping("/salas")
    public List<SalaDTO> getSalas() {
        List<Sala> salas = salaService.getAllSalas();
        SalaDTOConverter salaDTOConverter = new SalaDTOConverter();
        return salas.stream().map(salaDTOConverter::convertToDTO).collect(Collectors.toList());
    }
}