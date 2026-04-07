package com.finflow.finflow.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MetaResponse {
    private Long id;
    private String nome;
    private BigDecimal valorObjetivo;
    private BigDecimal valorAtual;
    private LocalDate prazo;
}