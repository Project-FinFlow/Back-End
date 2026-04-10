package com.finflow.finflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finflow.finflow.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}