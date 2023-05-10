package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.asiento.AsientoDTO;
import com.backend.pfg_haven.dto.asiento.AsientoDTOConverter;
import com.backend.pfg_haven.model.Asiento;
import com.backend.pfg_haven.repository.AsientoRepository;
import com.backend.pfg_haven.services.AsientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AsientoController {

    private final AsientoService asientoService;
    private final AsientoRepository asientoRepository;

    /**
     * Se obtienen todos los asientos de una sala en particular
     *
     * @return Lista de asientos
     */
    @GetMapping("/asientos/sala/{idSala}")
    public List<AsientoDTO> getAsientosBySala(@PathVariable Long idSala) {
        List<Asiento> asientos = asientoService.getAsientosBySala(idSala);
        AsientoDTOConverter converter = new AsientoDTOConverter();
        List<AsientoDTO> asientosDTO = asientos.stream().map(converter::convertToDTO).collect(Collectors.toList());
        asientosDTO.forEach(asientoDTO -> asientoDTO.setId_sala(idSala));
        return asientosDTO;
    }

    @GetMapping("/asientos/")
    public List<Asiento> getAllAsientos() {
        return asientoRepository.findAll();
    }


}
