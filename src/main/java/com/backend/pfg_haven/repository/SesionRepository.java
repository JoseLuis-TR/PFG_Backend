package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Long> {

    /**
     * Obtenemos todas las sesiones de hoy
     *
     * @return Lista de las sesiones de hoy
     */
    List<Sesion> findByFechaEquals(LocalDate fecha);

    /**
     * Obtenemos todas las sesiones a partir de una hoy
     *
     * @return Lista de las sesiones a partir de hoy
     */
    List<Sesion> findByFechaGreaterThanEqualOrderByFecha(LocalDate fecha);
}
