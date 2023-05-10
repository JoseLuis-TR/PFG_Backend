package com.backend.pfg_haven.services;

import com.backend.pfg_haven.model.Asiento;
import com.backend.pfg_haven.model.Reserva;
import com.backend.pfg_haven.model.ReservaTieneAsiento;
import com.backend.pfg_haven.repository.AsientoRepository;
import com.backend.pfg_haven.repository.ReservaRepository;
import com.backend.pfg_haven.repository.ReservaTieneAsientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaTieneAsientoService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private ReservaTieneAsientoRepository reservaTieneAsientoRepository;

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


}
