package com.backend.pfg_haven.dto.asiento;

import com.backend.pfg_haven.model.Asiento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsientoDTOConverter {

    private final ModelMapper modelMapper;

    public AsientoDTOConverter() {
        this.modelMapper = new ModelMapper();
    }

    public AsientoDTO convertToDTO(Asiento asiento){
        return modelMapper.map(asiento, AsientoDTO.class);
    }
}
