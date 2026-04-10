package com.finflow.finflow.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.finflow.finflow.dto.DespesaRequest;
import com.finflow.finflow.dto.DespesaResponse;
import com.finflow.finflow.model.Despesa;
import com.finflow.finflow.repository.DespesaRepository;
import com.finflow.finflow.mapper.DespesaMapper;

@Service
public class DespesaService {

    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    public DespesaResponse criar(DespesaRequest request) {
        Despesa despesa = DespesaMapper.toEntity(request);
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

        despesa.setDescricao(request.getDescricao());
        despesa.setValor(request.getValor());
        despesa.setData(request.getData());

        return DespesaMapper.toResponse(repository.save(despesa));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}