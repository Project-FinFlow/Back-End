package com.finflow.finflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finflow.finflow.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}