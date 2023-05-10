package com.backend.pfg_haven.services;

import com.backend.pfg_haven.model.Asiento;
import com.backend.pfg_haven.model.Sala;
import com.backend.pfg_haven.repository.AsientoRepository;
import com.backend.pfg_haven.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private SalaRepository salaRepository;

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

    /**
     * Función que se usa para generar los asientos de las salas
     * de manera automática, no se usa en la aplicación final
     */
    public void generateAsientos(){
        char[] letras = {'A', 'B', 'C', 'D', 'E'};
        for(long i = 1L; i < 4; i++){
            for(char letra: letras) {
                for(int c = 1; c < 13; c++) {
                    Asiento asiento = new Asiento();
                    asiento.setFila(letra);
                    asiento.setNumero(c);
                    Sala sala = salaRepository.findById(i).orElseThrow( () -> new ResourceNotFoundException(""));
                    asiento.setSala(sala);
                    asientoRepository.save(asiento);
                }
            }
        }
    }
}
