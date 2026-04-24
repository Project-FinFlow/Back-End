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
    private final LogSistemaService logService;

    public UsuarioService(UsuarioRepository repository, LogSistemaService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario user = UsuarioMapper.toEntity(request);
        Usuario salvo = repository.save(user);

        logService.registrar("USUARIO CRIADO");

        return UsuarioMapper.toResponse(salvo);
    }

    public List<UsuarioResponse> listar() {
        logService.registrar("USUARIO LISTADO");

        return repository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario user = repository.findById(id).orElseThrow();

        logService.registrar("USUARIO BUSCADO ID " + id);

        return UsuarioMapper.toResponse(user);
    }

    public void deletar(Long id) {
        repository.deleteById(id);

        logService.registrar("USUARIO DELETADO ID " + id);
    }
}