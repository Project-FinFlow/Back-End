package com.finflow.finflow.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DespesaResponse {
    private Long id;
    private BigDecimal valor;
    private String descricao;
    private LocalDate data;
}