package com.finflow.finflow.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MetaRequest {
    private String nome;
    private BigDecimal valorObjetivo;
    private LocalDate prazo;
    private Long usuarioId;
}