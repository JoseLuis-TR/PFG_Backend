package com.backend.pfg_haven.dto.pelicula;

import com.backend.pfg_haven.model.Pelicula;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PeliculaDTOConverter {

    private final ModelMapper modelMapper;

    public PeliculaDTOConverter() { this.modelMapper = new ModelMapper(); }

    public PeliculaCarteleraDTO convertToCarteleraDTO(Pelicula pelicula){ return modelMapper.map(pelicula, PeliculaCarteleraDTO.class);}
}
