package com.backend.pfg_haven.dto.reserva;

import com.backend.pfg_haven.model.Reserva;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservaDTOConverter {

    private final ModelMapper modelMapper;

    public ReservaDTOConverter() { this.modelMapper = new ModelMapper(); }

    public ReservaPostDTO convertToDTO(Reserva reserva){ return modelMapper.map(reserva, ReservaPostDTO.class);}
}
