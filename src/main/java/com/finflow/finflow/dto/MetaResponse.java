package com.finflow.finflow.dto;

public class MetaResponse {

    private Long id;
    private String descricao;
    private Double valorObjetivo;
    private Double valorAtual;
    private Long usuarioId;

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

    public Double getValorAtual() {
        return valorAtual;
    }

    public Long getUsuarioId() {
        return usuarioId;
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

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
