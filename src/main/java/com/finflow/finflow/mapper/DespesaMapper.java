package com.finflow.finflow.mapper;

import com.finflow.finflow.dto.DespesaRequest;
import com.finflow.finflow.dto.DespesaResponse;
import com.finflow.finflow.model.Despesa;

public class DespesaMapper {

    public static Despesa toEntity(DespesaRequest request) {
        Despesa despesa = new Despesa();
        despesa.setDescricao(request.getDescricao());
        despesa.setValor(request.getValor());
        despesa.setData(request.getData());
        return despesa;
    }

    public static DespesaResponse toResponse(Despesa despesa) {
        DespesaResponse response = new DespesaResponse();
        response.setId(despesa.getId());
        response.setDescricao(despesa.getDescricao());
        response.setValor(despesa.getValor());
        response.setData(despesa.getData());
        return response;
    }
}