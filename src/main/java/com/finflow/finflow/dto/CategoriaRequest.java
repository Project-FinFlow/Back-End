package com.finflow.finflow.dto;

import lombok.Data;

@Data
public class CategoriaRequest {
    private String nome;
    private String tipo; // RECEITA ou DESPESA
    private Long usuarioId;
}