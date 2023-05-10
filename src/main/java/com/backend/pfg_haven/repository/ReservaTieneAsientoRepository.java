package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.ReservaTieneAsiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaTieneAsientoRepository extends JpaRepository<ReservaTieneAsiento, Long> {

   List<ReservaTieneAsiento> findAllByReservaId (Long idReserva);

}
