package com.finflow.finflow.mapper;

import com.finflow.finflow.dto.MetaRequest;
import com.finflow.finflow.dto.MetaResponse;
import com.finflow.finflow.model.Meta;

public class MetaMapper {

    public static Meta toEntity(MetaRequest request) {
        Meta meta = new Meta();
        meta.setDescricao(request.getDescricao());
        meta.setValorObjetivo(request.getValorObjetivo());
        meta.setValorAtual(request.getValorAtual());
        return meta;
    }

    public static MetaResponse toResponse(Meta meta) {
        MetaResponse response = new MetaResponse();
        response.setId(meta.getId());
        response.setDescricao(meta.getDescricao());
        response.setValorObjetivo(meta.getValorObjetivo());
        response.setValorAtual(meta.getValorAtual());
        response.setUsuarioId(
            meta.getUsuario() != null ? meta.getUsuario().getId() : null
        );
        return response;
    }
}
