package com.finflow.finflow.dto;

public class MetaRequest {

    private String descricao;
    private Double valorObjetivo;

    public String getDescricao() {
        return descricao;
    }

    public Double getValorObjetivo() {
        return valorObjetivo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorObjetivo(Double valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }
}