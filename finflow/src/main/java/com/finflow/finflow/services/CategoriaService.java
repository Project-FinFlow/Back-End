package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.CategoriaRequest;
import com.finflow.finflow.dto.CategoriaResponse;
import com.finflow.finflow.model.Categoria;
import com.finflow.finflow.model.TipoCategoria;
import com.finflow.finflow.repository.CategoriaRepository;
import com.finflow.finflow.mapper.CategoriaMapper;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public CategoriaResponse criar(CategoriaRequest request) {
        Categoria categoria = CategoriaMapper.toEntity(request);
        return CategoriaMapper.toResponse(repository.save(categoria));
    }

    public List<CategoriaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(CategoriaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public CategoriaResponse buscarPorId(Long id) {
        Categoria categoria = repository.findById(id).orElseThrow();
        return CategoriaMapper.toResponse(categoria);
    }

    public CategoriaResponse atualizar(Long id, CategoriaRequest request) {
        Categoria categoria = repository.findById(id).orElseThrow();

        categoria.setNome(request.getNome());

        // 🔥 CORREÇÃO DO ENUM
        categoria.setTipo(
            TipoCategoria.valueOf(request.getTipo().toUpperCase())
        );

        return CategoriaMapper.toResponse(repository.save(categoria));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}