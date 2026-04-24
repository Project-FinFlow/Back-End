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
    private final LogSistemaService logService;

    public ReceitaService(ReceitaRepository repository, LogSistemaService logService) {
        this.repository = repository;
        this.logService = logService;
    }

    public ReceitaResponse criar(ReceitaRequest request) {
        Receita receita = ReceitaMapper.toEntity(request);
        Receita salvo = repository.save(receita);

        logService.registrar("RECEITA CRIADA");

        return ReceitaMapper.toResponse(salvo);
    }

    public List<ReceitaResponse> listar() {
        logService.registrar("RECEITA LISTADA");

        return repository.findAll()
                .stream()
                .map(ReceitaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ReceitaResponse buscarPorId(Long id) {
        Receita receita = repository.findById(id).orElseThrow();

        logService.registrar("RECEITA BUSCADA ID " + id);

        return ReceitaMapper.toResponse(receita);
    }

    public ReceitaResponse atualizar(Long id, ReceitaRequest request) {
        Receita receita = repository.findById(id).orElseThrow();

        receita.setDescricao(request.getDescricao());
        receita.setValor(request.getValor());
        receita.setData(request.getData());

        Receita atualizado = repository.save(receita);

        logService.registrar("RECEITA ATUALIZADA ID " + id);

        return ReceitaMapper.toResponse(atualizado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);

        logService.registrar("RECEITA DELETADA ID " + id);
    }
}