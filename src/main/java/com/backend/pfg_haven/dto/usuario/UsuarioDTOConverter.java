package com.backend.pfg_haven.dto.usuario;

import com.backend.pfg_haven.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioDTOConverter {

    private final ModelMapper modelMapper;

    public UsuarioDTOConverter() { this.modelMapper = new ModelMapper(); }

    public UsuarioDTO convertToDTO(Usuario usuario){ return modelMapper.map(usuario, UsuarioDTO.class);}
}
