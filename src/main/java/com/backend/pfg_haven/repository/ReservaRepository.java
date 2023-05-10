package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

    List<Reserva> findBySesionId(Long idSesion);
}
