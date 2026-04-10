package com.finflow.finflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finflow.finflow.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}