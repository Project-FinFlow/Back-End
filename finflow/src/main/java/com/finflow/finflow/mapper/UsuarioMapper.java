package com.finflow.finflow.mapper;

import com.finflow.finflow.dto.UsuarioRequest;
import com.finflow.finflow.dto.UsuarioResponse;
import com.finflow.finflow.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario user = new Usuario();
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setSenha(request.getSenha());
        return user;
    }

    public static UsuarioResponse toResponse(Usuario user) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(user.getId());
        response.setNome(user.getNome());
        response.setEmail(user.getEmail());
        return response;
    }
}