package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.DespesaRequest;
import com.finflow.finflow.dto.DespesaResponse;
import com.finflow.finflow.model.Categoria;
import com.finflow.finflow.model.Despesa;
import com.finflow.finflow.model.Usuario;
import com.finflow.finflow.repository.CategoriaRepository;
import com.finflow.finflow.repository.DespesaRepository;
import com.finflow.finflow.repository.UsuarioRepository;
import com.finflow.finflow.mapper.DespesaMapper;

@Service
public class DespesaService {

    private final DespesaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public DespesaService(
            DespesaRepository repository,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public DespesaResponse criar(DespesaRequest request) {

        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            throw new RuntimeException("Descrição é obrigatória");
        }

        if (request.getValor() == null) {
            throw new RuntimeException("Valor é obrigatório");
        }

        if (request.getData() == null) {
            throw new RuntimeException("Data é obrigatória");
        }

        Despesa despesa = DespesaMapper.toEntity(request);
        preencherRelacionamentos(despesa, request);
        return DespesaMapper.toResponse(repository.save(despesa));
    }

    public List<DespesaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(DespesaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public DespesaResponse buscarPorId(Long id) {
        Despesa despesa = repository.findById(id).orElseThrow();
        return DespesaMapper.toResponse(despesa);
    }

    public DespesaResponse atualizar(Long id, DespesaRequest request) {

        Despesa despesa = repository.findById(id).orElseThrow();

        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            throw new RuntimeException("Descrição é obrigatória");
        }

        if (request.getValor() == null) {
            throw new RuntimeException("Valor é obrigatório");
        }

        if (request.getData() == null) {
            throw new RuntimeException("Data é obrigatória");
        }

        despesa.setDescricao(request.getDescricao());
        despesa.setValor(request.getValor());
        despesa.setData(request.getData());
        preencherRelacionamentos(despesa, request);

        return DespesaMapper.toResponse(repository.save(despesa));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private void preencherRelacionamentos(Despesa despesa, DespesaRequest request) {
        Usuario usuario = null;
        Categoria categoria = null;

        if (request.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        }

        if (request.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));
        }

        despesa.setUsuario(usuario);
        despesa.setCategoria(categoria);
    }
}
