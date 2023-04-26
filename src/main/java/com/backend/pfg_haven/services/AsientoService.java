package com.backend.pfg_haven.services;

import com.backend.pfg_haven.model.Asiento;
import com.backend.pfg_haven.repository.AsientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    /**
     * Obtenemos todos los asientos de una sala
     *
     * @param idSala
     * @return Lista de asientos
     */
    public List<Asiento> getAsientosBySala(Long idSala) {
        List<Asiento> asientos = asientoRepository.findBySalaId(idSala);
        if (asientos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron asientos para la sala con id: " + idSala);
        } else {
            return asientos;
        }
    }
}
