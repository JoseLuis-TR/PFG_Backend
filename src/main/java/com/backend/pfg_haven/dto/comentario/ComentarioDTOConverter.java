package com.backend.pfg_haven.dto.comentario;

import com.backend.pfg_haven.model.Comentario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComentarioDTOConverter {

    private final ModelMapper modelMapper;

    public ComentarioDTOConverter() { this.modelMapper = new ModelMapper(); }

    public ComentarioDTO convertToDTO(Comentario comentario){ return modelMapper.map(comentario, ComentarioDTO.class);}
}
