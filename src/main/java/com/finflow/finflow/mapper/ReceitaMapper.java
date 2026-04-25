package com.finflow.finflow.mapper;

import com.finflow.finflow.dto.ReceitaRequest;
import com.finflow.finflow.dto.ReceitaResponse;
import com.finflow.finflow.model.Receita;

public class ReceitaMapper {

    public static Receita toEntity(ReceitaRequest request) {
        Receita receita = new Receita();
        receita.setDescricao(request.getDescricao());
        receita.setValor(request.getValor());
        receita.setData(request.getData());
        return receita;
    }

    public static ReceitaResponse toResponse(Receita receita) {
        ReceitaResponse response = new ReceitaResponse();
        response.setId(receita.getId());
        response.setDescricao(receita.getDescricao());
        response.setValor(receita.getValor());
        response.setData(receita.getData());
        response.setUsuarioId(
            receita.getUsuario() != null ? receita.getUsuario().getId() : null
        );
        response.setCategoriaId(
            receita.getCategoria() != null ? receita.getCategoria().getId() : null
        );
        return response;
    }
}
