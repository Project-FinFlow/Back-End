package com.finflow.finflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finflow.finflow.model.Meta;

public interface MetaRepository extends JpaRepository<Meta, Long> {
}