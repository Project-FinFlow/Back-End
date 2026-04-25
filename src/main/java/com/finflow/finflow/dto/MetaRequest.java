package com.finflow.finflow.dto;

public class MetaRequest {

    private String descricao;
    private Double valorObjetivo;
    private Double valorAtual;
    private Long usuarioId;

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
