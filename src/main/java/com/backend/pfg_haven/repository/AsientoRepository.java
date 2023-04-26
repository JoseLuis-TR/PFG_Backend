package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsientoRepository extends JpaRepository<Asiento, Long>{

    /**
     * Obtenemos todos los asientos de una sala
     *
     * @param idSala
     * @return Lista de asientos
     */
    List<Asiento> findBySalaId(Long idSala);


}
