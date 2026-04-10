package com.finflow.finflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finflow.finflow.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}