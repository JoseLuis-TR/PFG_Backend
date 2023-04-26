package com.backend.pfg_haven.repository;

import com.backend.pfg_haven.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
