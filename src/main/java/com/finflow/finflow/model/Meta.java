package com.finflow.finflow.model;

import jakarta.persistence.*;

@Entity
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Double valorObjetivo;

    private Double valorAtual;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // GETTERS E SETTERS

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

    public Usuario getUsuario() {
        return usuario;
    }

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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
