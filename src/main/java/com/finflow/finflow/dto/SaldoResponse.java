package com.finflow.finflow.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SaldoResponse {
    private BigDecimal saldoAtual;
    private BigDecimal totalReceitas;
    private BigDecimal totalDespesas;
}