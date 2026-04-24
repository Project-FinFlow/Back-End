package com.finflow.finflow.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldoAtual;

    private BigDecimal totalReceitas;

    private BigDecimal totalDespesas;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}