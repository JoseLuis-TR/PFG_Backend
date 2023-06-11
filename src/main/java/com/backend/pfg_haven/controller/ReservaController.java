package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.dto.reserva.ReservaPostDTO;
import com.backend.pfg_haven.model.Reserva;
import com.backend.pfg_haven.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    /**
     * Añadimos una nueva reserva a la base de datos
     */
    @PostMapping("/reservas")
    public Reserva addReserva(@RequestBody ReservaPostDTO newReserva){
        return reservaService.addReserva(newReserva);
    }
}
