package com.finflow.finflow.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LogSistemaResponse {
    private Long id;
    private String tabela;
    private Long registroId;
    private String acao;
    private String descricao;
    private LocalDateTime dataHora;
}