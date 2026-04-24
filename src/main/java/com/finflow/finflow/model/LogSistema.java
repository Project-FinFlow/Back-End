package com.finflow.finflow.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tabela; // ex: "receita", "despesa".

    private Long registroId; // id do registro alterado.

    private String acao; // CREATE, UPDATE, DELETE.

    private String descricao;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}