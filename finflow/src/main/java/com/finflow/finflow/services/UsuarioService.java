package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.UsuarioRequest;
import com.finflow.finflow.dto.UsuarioResponse;
import com.finflow.finflow.model.Usuario;
import com.finflow.finflow.repository.UsuarioRepository;
import com.finflow.finflow.mapper.UsuarioMapper;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario user = UsuarioMapper.toEntity(request);
        return UsuarioMapper.toResponse(repository.save(user));
    }

    public List<UsuarioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario user = repository.findById(id).orElseThrow();
        return UsuarioMapper.toResponse(user);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}