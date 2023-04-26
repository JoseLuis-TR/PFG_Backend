package com.backend.pfg_haven.services;

import com.backend.pfg_haven.model.Sala;
import com.backend.pfg_haven.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> getAllSalas() {
        return salaRepository.findAll();
    }
}