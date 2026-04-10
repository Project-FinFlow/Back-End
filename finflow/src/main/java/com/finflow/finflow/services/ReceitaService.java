package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.ReceitaRequest;
import com.finflow.finflow.dto.ReceitaResponse;
import com.finflow.finflow.model.Receita;
import com.finflow.finflow.repository.ReceitaRepository;
import com.finflow.finflow.mapper.ReceitaMapper;

@Service
public class ReceitaService {

    private final ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    public ReceitaResponse criar(ReceitaRequest request) {
        Receita receita = ReceitaMapper.toEntity(request);
        return ReceitaMapper.toResponse(repository.save(receita));
    }

    public List<ReceitaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(ReceitaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ReceitaResponse buscarPorId(Long id) {
        Receita receita = repository.findById(id).orElseThrow();
        return ReceitaMapper.toResponse(receita);
    }

    public ReceitaResponse atualizar(Long id, ReceitaRequest request) {
        Receita receita = repository.findById(id).orElseThrow();

        receita.setDescricao(request.getDescricao());
        receita.setValor(request.getValor());
        receita.setData(request.getData());

        return ReceitaMapper.toResponse(repository.save(receita));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}