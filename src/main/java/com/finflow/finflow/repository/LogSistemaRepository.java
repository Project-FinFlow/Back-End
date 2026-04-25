package com.finflow.finflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finflow.finflow.model.LogSistema;

public interface LogSistemaRepository extends JpaRepository<LogSistema, Long> {
    boolean existsByDescricao(String descricao);
}
