package com.backend.pfg_haven.dto.sesion;

import com.backend.pfg_haven.model.Sesion;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SesionDTOConverter {

    private final ModelMapper modelMapper;

    public SesionDTOConverter() { this.modelMapper = new ModelMapper(); }

    public SesionDTO convertToDTO(Sesion sesion){ return modelMapper.map(sesion, SesionDTO.class);}
}
