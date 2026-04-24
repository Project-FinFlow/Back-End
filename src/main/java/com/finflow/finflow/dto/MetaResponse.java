package com.finflow.finflow.dto;

public class MetaResponse {

    private Long id;
    private String descricao;
    private Double valorObjetivo;

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValorObjetivo() {
        return valorObjetivo;
    }

    // SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorObjetivo(Double valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }
}