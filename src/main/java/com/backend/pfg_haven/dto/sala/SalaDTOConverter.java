package com.backend.pfg_haven.dto.sala;

import com.backend.pfg_haven.model.Sala;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalaDTOConverter {

    private final ModelMapper modelMapper;

    public SalaDTOConverter() { this.modelMapper = new ModelMapper(); }

    public SalaDTO convertToDTO(Sala sala){ return modelMapper.map(sala, SalaDTO.class);}
}
