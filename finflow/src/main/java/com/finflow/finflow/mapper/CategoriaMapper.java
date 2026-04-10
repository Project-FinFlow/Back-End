package com.finflow.finflow.mapper;

import com.finflow.finflow.dto.CategoriaRequest;
import com.finflow.finflow.dto.CategoriaResponse;
import com.finflow.finflow.model.Categoria;
import com.finflow.finflow.model.TipoCategoria;

public class CategoriaMapper {

    public static Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.getNome());

        categoria.setTipo(
            TipoCategoria.valueOf(request.getTipo().toUpperCase())
        );

        return categoria;
    }

    public static CategoriaResponse toResponse(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        response.setId(categoria.getId());
        response.setNome(categoria.getNome());
        response.setTipo(categoria.getTipo().name());
        return response;
    }
}