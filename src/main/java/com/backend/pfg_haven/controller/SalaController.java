package com.backend.pfg_haven.controller;

import com.backend.pfg_haven.model.Sala;
import com.backend.pfg_haven.services.SalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @GetMapping("/salas")
    public List<Sala> getSalas() {
        return salaService.getAllSalas();
    }
}
